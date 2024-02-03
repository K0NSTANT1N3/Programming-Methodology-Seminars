package exams.finaleExamProblem.final_mario;

import acm.program.ConsoleProgram;

public class MostProgram extends ConsoleProgram {

    public void run() {
        println(racxa(x, 2));
        println(racxa(y, x / 2 + x));
    }
    public int racxa(int x, int y) {
        x = varesiRacxa(x / y, x + y);
        y = varesiRacxa(x, y);
        return x + y;
    }

    public int varesiRacxa(int x, int y) {
        int z = x + 'z' - 'w';
        return z + this.x;
    }
    private int x = 1;
    private int y = 2;
}
