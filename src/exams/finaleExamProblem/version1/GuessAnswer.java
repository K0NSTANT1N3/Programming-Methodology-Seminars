package exams.finaleExamProblem.version1;

import acm.program.ConsoleProgram;

public class GuessAnswer extends ConsoleProgram {
    public void run() {
        int arr[] = new int[100];
        for (int i=0; i<10; ) {
            for (int j=0; j<5; j++) {
                i++;
                arr[i+j] = i+2*j;
            }
        }
        for (int i=0; i<10; i++) {
            System.out.println(arr[i] + " ");
        }
    }
}
