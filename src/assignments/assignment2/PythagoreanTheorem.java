/*
 * File: PythagoreanTheorem.java
 * Name: Konstantine Endeladze
 * Section Leader: 
 * -----------------------------
 * This file finds hipotenusis in pythagorean problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		int a = readInt("a: ");
		int b = readInt("b: ");

		double c = findHipotenus(a, b);
		println("c = " + c);
	}

	// calculates square root of sum of two integer
	private double findHipotenus(int a, int b){
		double SquaredC = a * a * 1.0 + b * b;
		double c = Math.sqrt(SquaredC);
		return c;
	}
}
