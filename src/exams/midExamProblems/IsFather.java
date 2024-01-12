package exams.midExamProblems;

import acm.program.ConsoleProgram;

public class IsFather extends ConsoleProgram {
    public void run() {
        String father = readLine();
        String son = readLine();

        println(isFather(father, son));
    }

    private boolean isFather(String father, String son) {
        int minMatched = father.length() / 2 + 1;
        if (minMatched > son.length()) return false;

        for (int i = 0; i <= father.length() - minMatched; i++) {
            String part = father.substring(i, i + minMatched);

            if (son.contains(part)) return true;
        }
        return false;
    }
}
