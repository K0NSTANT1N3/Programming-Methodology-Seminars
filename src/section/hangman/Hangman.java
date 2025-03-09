package section.hangman;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

public class Hangman extends ConsoleProgram {

	private RandomGenerator rgen = RandomGenerator.getInstance(); // We need to
																	// select a
																	// word.
	private HangmanCanvas canvas;

	// Bring the canvas to the screen
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
		canvas.reset();
	}

	public void run() {
		HangmanLexicon lexicon = new HangmanLexicon();
		int index = rgen.nextInt(0, lexicon.getWordCount() - 1);
		String word = lexicon.getWord(index); // This selects a random word.
		String currentWord = ""; // Initially, we don't know any of the letters
									// of the word.
		currentWord = firstUnknownWord(word); // How many letters does the word
												// consist of?
		canvas.displayWord(currentWord); // We draw the initial state of the
											// hangman.
		int tryCount = 8; // How many mistakes is a player allowed to make?
		start(currentWord, tryCount); // Initial information for the player
		gameProcess(currentWord, tryCount, word); //

	}

	// A method by which a player learns how many letters a word consists of.
	private String firstUnknownWord(String word) {
		String firstWord = "";
		for (int i = 0; i < word.length(); i++) {
			firstWord += "_ ";
		}
		return firstWord;
	}

	// The player learns how many attempts he has at the beginning and how many
	// letters the word consists of.
	private void start(String currentWord, int tryCount) {
		println("Welcome to Hangman!");
		println("The word now looks like this: " + currentWord);
		println("You have " + tryCount + " guesses left.");

	}

	private void gameProcess(String currentWord, int tryCount, String word) {
		while (true) {
			// This will fix a bug that occurs if the length of the input
			// character is zero.
			String ch = null;

			if (ch == null) {
				ch = readLine("Your guess: ");
				if (ch.length() == 0) {
					println("ENTER SYMBOL");
					ch = "";
					continue;
				}
			}
			// Message that the entered string is not a letter
			if (ch.length() > 1) {
				firstAdditionalCase(currentWord, tryCount, ch);
				continue;
			}
			// Message that the entered character is not a letter
			if (!Character.isLetter(ch.charAt(0))) {
				secondAdditionalCase(currentWord, tryCount, ch);
				continue;
			}
			// If the player has already entered a letter that is in the word,
			// he/she must try again.
			if (currentWord.contains(ch.toUpperCase())) {
				SameLetter(currentWord, tryCount);
				continue;
			}
			// After each guessed letter, it must be added to the word on the
			// player's screen.
			currentWord = newCurrentWord(ch.toUpperCase(), word, currentWord);
			canvas.displayWord(currentWord); // The current word should also be
												// displayed on the canvas
												// screen.

			// A player should know when he has won the game.
			if (currentWord.indexOf('_') == -1) {
				win(word);
				break;
			}

			if (word.contains(ch.toUpperCase())) {
				printIfYouAreCorrect(currentWord, tryCount);
				// A new organ should be added for each mistake.
			} else {
				tryCount--;
				if (tryCount == 7) {
					canvas.drawHead();
				}
				if (tryCount == 6) {
					canvas.drawBody();
				}
				if (tryCount == 5) {
					canvas.drawLeftHand();
				}
				if (tryCount == 4) {
					canvas.drawRightHand();
				}
				if (tryCount == 3) {
					canvas.drawLeftLeg();
				}
				if (tryCount == 2) {
					canvas.drawRightLeg();
				}
				if (tryCount == 1) {
					canvas.drawLeftFoot();
				}
				printIfYouAreWrong(currentWord, tryCount, ch, word);
				canvas.noteIncorrectGuess(ch);
				// A player must know when he is losing.
				if (tryCount == 0) {
					canvas.drawRightFoot();
					lose(word);
					break;
				}
			}
		}
		// The player must decide whether to continue playing or not.
		// If the player wants to continue the game, he/she must enter "YES",
		// and if he/she does not want to, "NO".
		while (true) {
			if (tryCount == 0 || currentWord.indexOf("_") == -1) {
				String decision = readLine("Do you want to start over? (yes or no): ");
				decision = decision.toUpperCase();
				if (decision.equals("YES")) {
					canvas.reset();
					run();
				}
				if (decision.equals("NO")) {
					println("Goodbye");
					pause(3000); // The game is over in three seconds.
					System.exit(0);
				}

				while (!decision.equals("YES") && !decision.equals("NO")) {
					println("ONLY YES OR NO");
					decision = readLine("Do you want to start over? : ");
					decision = decision.toUpperCase();
					if (decision.equals("YES")) {
						canvas.reset();
						run(); // The game will start over.
					}
					if (decision.equals("NO")) {
						println("Goodbye");
						pause(3000); // The game is over in three seconds.
						System.exit(0);

					}
				}
			}
		}
	}

	// This method changes the current word.
	private String newCurrentWord(String ch, String word, String currentWord) {
		String currentUnknownWord = "";
		for (int i = 0; i < currentWord.length(); i += 2) {
			if (word.charAt(i / 2) == ch.charAt(0)) {
				currentUnknownWord += (ch + " ");
			} else {
				currentUnknownWord += (currentWord.charAt(i) + " ");
			}
		}
		return currentUnknownWord;
	}

	//// Message that the entered string is not a letter
	private void firstAdditionalCase(String currentWord, int tryCount, String ch) {
		println("You must enter only one character, please try again.");
		println("The word now looks like this: " + currentWord);
		println("You have " + tryCount + " guesses left.");

	}

	//// Message that the entered character is not a letter
	private void secondAdditionalCase(String currentWord, int tryCount, String ch) {
		println("The character you entered is not a letter, please try again.");
		println("The word now looks like this: " + currentWord);
		println("You have " + tryCount + " guesses left.");

	}

	// If the player has already entered a letter that is in the word
	private void SameLetter(String currentWord, int tryCount) {
		println("The entered letter already exists in a word.");
		println("The word now looks like this: " + currentWord);
		if (tryCount > 1) {
			println("You have " + tryCount + " guesses left.");
		}
		if (tryCount == 1) {
			println("You have only one guess left.");
		}

	}

	private void win(String word) {
		println("That guess is correct.");
		println("You guessed the word: " + word);
		println("You win.");

	}

	private void printIfYouAreCorrect(String currentWord, int tryCount) {
		println("That guess is correct.");
		println("The word now looks like this: " + currentWord);
		if (tryCount > 1) {
			println("You have " + tryCount + " guesses left.");
		}
		if (tryCount == 1) {
			println("You have only one guess left.");
		}

	}

	// If you enter an incorrect letter, the number of attempts will be reduced.
	private void printIfYouAreWrong(String currentWord, int tryCount, String ch, String word) {
		println("There are no " + ch.toUpperCase() + "'s in the word.");
		if (tryCount > 1) {
			println("The word now looks like this: " + currentWord);
			println("You have " + tryCount + " guesses left.");
		}
		if (tryCount == 1) {
			println("The word now looks like this: " + currentWord);
			println("You have only one guess left.");
		}

	}

	private void lose(String word) {
		println("You are completely hung.");
		println("The word was: " + word);
		println("You lose.");

	}

}
