/*
 * File: PythagoreanTheorem.java
 * Name:
 * Section Leader:
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println("Enter values to compute hypotenuse.");
		int a = readInt("Enter a: ");
		int b = readInt("Enter b:");
		if((a > 0)&&(b > 0)){
			println("The hypotenuse equals "+ calcHyp(a,b) +".");
		}else{
			println("Wrong number, length must be positive.");
		}
	}


	//calculates hypotenuse.
	private double calcHyp(int a,int b) {
		double c = Math.sqrt(a*a + b*b);
		return c;

	}
}
