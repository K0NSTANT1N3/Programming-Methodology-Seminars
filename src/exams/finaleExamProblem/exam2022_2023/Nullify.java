package exams.finaleExamProblem.exam2022_2023;

import acm.program.ConsoleProgram;

import java.util.StringTokenizer;

public class Nullify extends ConsoleProgram {

    private void nullify(int[][] matrix) {
        StringBuilder xZero = new StringBuilder();
        StringBuilder yZero = new StringBuilder();
        int numZero = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    xZero.append(i).append(" ");
                    yZero.append(j).append(" ");
                    numZero++;
                }
            }
        }

        StringTokenizer tokenx = new StringTokenizer(xZero.toString());
        StringTokenizer tokeny = new StringTokenizer(yZero.toString());

        for (int i = 0; i < numZero; i++) {
            int x = Integer.parseInt(tokenx.nextToken());
            int y = Integer.parseInt(tokeny.nextToken());

            for (int j = 0; j < matrix.length; j++) {
               matrix[j][y] = 0;
            }
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[x][j] = 0;
            }
        }
    }
}
