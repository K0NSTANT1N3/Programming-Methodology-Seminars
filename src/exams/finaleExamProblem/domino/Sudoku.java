package exams.finaleExamProblem.domino;

import acm.program.ConsoleProgram;

import java.util.HashSet;

public class Sudoku extends ConsoleProgram {

    public int[][] solveSudoku(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    HashSet<Integer> used = usedNums(sudoku, i, j);

                    int sum = 0;
                    for (int itr : used) {
                        sum += itr;
                    }
                    sum = 45 - sum;

                    sudoku[i][j] = sum;
                }
            }
        }
        return sudoku;
    }

    private HashSet<Integer> usedNums(int[][] sudoku, int x, int y) {
        HashSet<Integer> used = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            used.add(sudoku[x][i]);
            used.add(sudoku[i][y]);
        }

        int startx = (x / 3) * 3;
        int starty = (y / 3) * 3;

        for (int i = startx; i < startx + 3; i++) {
            for (int j = starty; j < starty + 3; j++) {
                used.add(sudoku[i][j]);
            }
        }

        return used;
    }
}
