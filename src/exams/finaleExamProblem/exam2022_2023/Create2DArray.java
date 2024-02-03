package exams.finaleExamProblem.exam2022_2023;

import acm.program.ConsoleProgram;

import java.util.Arrays;

public class Create2DArray extends ConsoleProgram {

    public void run(){
        int[] arr = new int[readInt()];
        for (int i = 0; i < arr.length; i++){
            arr[i] = readInt();
        }

        System.out.println(Arrays.deepToString(create(arr)));
    }

    public int[][] create(int[] arr) {
        int a[][] = new int[arr.length][];

        for (int i = 0; i < arr.length; i++) {
            int[] row = new int[arr[i]];
            a[i] = row;
        }

        return a;
    }

}
