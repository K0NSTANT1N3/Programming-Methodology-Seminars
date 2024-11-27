/*
 * File: Hailstone.java
 * Name:
 * Section Leader:
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
    public void run() {
        int steps = 0;
        int num = readInt("Enter a number: ");
        if(num <= 0) {
            println("Number must be Natural.");
        }else{
            while(num != 1){
                int pre = num;
                steps++;
                if(num % 2 ==0){
                    num /= 2;
                    println(pre+"is even, so I take half: "+num);
                }else{
                    num = 3*num + 1;
                    println(pre+"is odd, so I make 3n+1:"+num);

                }

            }
            println("The process took "+ steps +" to reach 1");

        }


    }
}
