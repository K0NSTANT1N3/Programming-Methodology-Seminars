package exams.finaleExamProblem.version1.domino;

import acm.program.ConsoleProgram;

public class WhatWillBePrint extends ConsoleProgram {
    public void run() {
        int arr[] = new int[100];
        for (int i = 0; i < 10; ) {
            for (int j = 0; j < 8; j++) {
                j++;
                arr[i + j] = i + 2 * j;
                i++;
            }
        }
        for (int i = 0; i < 10; i++) {
            print(arr[i] + " ");
        }
    }
}

//0 2 0 0 7 6 0 12 11 10