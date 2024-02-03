package exams.finaleExamProblem.madeUpProblems;

import acm.program.ConsoleProgram;

public class SimilarStrings extends ConsoleProgram {

    public boolean StringsAreSimilar(String a, String b) {
        int n = a.length();
        for (int i = 0; i <= n / 2; i++) {
            if (2 * i + 1 == n) {
                if (a.charAt(i) == b.charAt(i))return false;
            }

            if(!isOk(a.charAt(i), b.charAt(i), a.charAt(n - i - 1), b.charAt(n - i - 1)))return false;
        }
        return true;
    }

    private boolean isOk(char a, char b, char c, char d) {
        if (a == b && c == d) {
            return true;
        } else if (a == c && b == d) {
            return true;
        } else if (a == d && b == d) {
            return true;
        } else if (a == b && b == c && c == d) {
            return true;
        }
        return false;
    }
}
