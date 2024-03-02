/*
 * File: FindRange.java
 * Name: Konstantine Endeladze
 * Section Leader: 
 * --------------------
 * This file solves FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int SENTINEL = 0;

	public void run() {
		println("This program finds the largest and smallest numbers");

		//stop the program if x is SENTINEL
		int x = readInt(), max, min;
		if(x==SENTINEL)println("No min or max is inputed");

		else{
			min = max = x;
			//change min and max untill x is equal to SENTINEL
			while(x != SENTINEL){
				max = max < x ? x: max;
				min = min > x ? x: min;
				x = readInt();
			}
			//print answer
			println("smallest: " + min);
			println("biggest: " + max);
		}
	}
}

