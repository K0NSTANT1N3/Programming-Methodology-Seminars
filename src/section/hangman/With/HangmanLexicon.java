package section.hangman.With;/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */
import java.io.*;
import java.util.ArrayList;

public class HangmanLexicon {
	// Array of words
	private final ArrayList<String> lexicon = new ArrayList<>();

	// Lexicon file
	private final InputStream lexiconFile = getClass().getResourceAsStream("/HangmanLexicon.txt");

	// Constructor
	public HangmanLexicon() {
		try {
			// Read the file
			BufferedReader rd = new BufferedReader(new InputStreamReader(lexiconFile));

			// Add each word to array
			String line;
			while ((line = rd.readLine()) != null) {
				lexicon.add(line);
			}

			// Close file
			rd.close();
		}catch (IOException e){
			throw new RuntimeException(e);
		}
	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexicon.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return lexicon.get(index);
	}
}
