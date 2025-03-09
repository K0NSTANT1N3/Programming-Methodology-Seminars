package section.hangman;/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.*;

public class HangmanLexicon {
	
	//instance variables
	
	//list containing every word
	ArrayList<String> wordList = new ArrayList<String>();
	
	//takes words from a hangmanlexicon.txt file and puts it into an arraylist
	public HangmanLexicon(){
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			
			while(true){
				String line = reader.readLine();
				if(line == null)break;
				wordList.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return wordList.get(index);
	}
}
