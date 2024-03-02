/*
 * File: Hailstone.java
 * Name: Konstantine Endeladze
 * Section Leader: Archili
 * --------------------
 * This is numerical demonstration for hailstone problem
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
    public void run() {
        int number = readInt("Enter a number: ");
        hailstoneProcess(number);
    }

    // does hailstone process till end
    private void hailstoneProcess(int number) {
        int counter = 0;
        String ascend = " is odd, so I make 3n + 1: ";
        String descend = " is even, so I take half: ";
        while (number > 1) {
            counter++;
            int current = number;
            number = hailstoneStep(number);
            if (number % 2 == 0) {
                println(current + descend + number);
            } else {
                println(current + ascend + number);
            }
        }
        println("The process took " + counter + " to reach 1");
    }

    // change number according to rules
    private int hailstoneStep(int n) {
        return (n % 2 == 0) ? n / 2 : n * 3 + 1;
    }
}

