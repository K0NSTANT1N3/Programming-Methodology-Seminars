package assignments.hangman;/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HangmanLexicon {

    ArrayList<String> wordContainer;

    public HangmanLexicon() throws IOException {
		BufferedReader reader;
		try {
			String src = "src/assignments.hangman/lexicon/ShorterLexicon.txt"; //modify based on hierarchy of ur code
			FileReader fileReader = new FileReader(src);
			reader = new BufferedReader(fileReader);
		} catch (IOException e) {
			throw new IOException(e);
		}

		wordContainer = new ArrayList<>();
		String word;
		while ((word = reader.readLine()) != null) {
			wordContainer.add(word);
		}
		reader.close();
    }

    /**
     * Returns the number of words in the lexicon.
     */
    public int getWordCount() {
        return wordContainer.size();
    }

    /**
     * Returns the word at the specified index.
     */
	public String getWord(int index) {
		try{
			return wordContainer.get(index);
		}catch (ArrayIndexOutOfBoundsException e){
			throw new ArrayIndexOutOfBoundsException(String.valueOf(e));
		}
	}
}
