package exams.finaleExamProblem.domino;

import acm.program.ConsoleProgram;

import java.util.StringTokenizer;

public class Title extends ConsoleProgram {

    public String translateToTitle(String text) {
        String ans = "";
        StringTokenizer token = new StringTokenizer(text);

        if (token.hasMoreTokens()) {
            String word = token.nextToken();
            word = (word.substring(0, 1).toUpperCase()) + word.substring(1).toLowerCase();
            ans += word;
        }
        while (token.hasMoreTokens()) {
            ans += " ";
            String word = token.nextToken();
            word = (word.substring(0, 1).toUpperCase()) + word.substring(1).toLowerCase();
            ans += word;
        }
        return ans;
    }
}
