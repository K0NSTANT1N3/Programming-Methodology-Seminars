/*
 * File: FindRange.java
 * Name:
 * Section Leader:
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

	private static final int SENTINEL = 0;

	public void run() {
		int start = readInt("Enter numbers: "); // first number entered by user.
		int max = start;
		int min = start;
		if(start == SENTINEL){
			println("This number stops program. Enter other integers.");
		}else{
			while(true){
				int num = readInt("Enter integer: ");
				if(num == SENTINEL){
					println("max="+max+", min="+min);
					break;
				}

				if(max <= num) max = num;   //determines if new number is more than max.
				if(min >= num) min = num;   //determines if new number is less tha min.

			}
		}


	}
}

