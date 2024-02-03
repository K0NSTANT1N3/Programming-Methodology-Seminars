package exams.finaleExamProblem.final_mario;

import acm.program.ConsoleProgram;

import java.util.HashSet;
import java.util.StringTokenizer;

public class longestWord extends ConsoleProgram {

    private String getWord(String text) {
        String ans = "";
        StringTokenizer token = new StringTokenizer(text);

        while (token.hasMoreTokens()) {
            String tmp = token.nextToken();
            tmp.replaceAll(".",  "");
            if(uniqueLetters(tmp)){
                ans =  tmp.length() > ans.length() ? tmp : ans;
            }
        }
        return ans;
    }

    private boolean uniqueLetters(String str) {
        HashSet<Character> chars = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            chars.add(str.charAt(i));
        }
        return chars.size() == str.length();
    }
}
