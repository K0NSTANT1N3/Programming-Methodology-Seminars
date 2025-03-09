package section.hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HangmanLexicon {
	private ArrayList<String> wordsList = new ArrayList<String>();

	// This method reads all the words from the HangmanLexicon file line by line
	// and adds them to the list.
	public HangmanLexicon() {
		BufferedReader rd = null;

		try {
			String src = "src/assignments/hangman/lexicon/ShorterLexicon.txt";
			rd = new BufferedReader(new FileReader(src));
			String line;
			while ((line = rd.readLine()) != null) {
				wordsList.add(line);
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return wordsList.get(index);
	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordsList.size();
	}

}
