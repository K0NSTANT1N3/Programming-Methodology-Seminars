package exams.finaleExamProblem.version1.final_exam;

import acm.program.ConsoleProgram;

public class Transition extends ConsoleProgram {

    public String blowup(String str) {
        String s = str;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int x = s.charAt(i) - '0';
                if (i >= s.length() - 1) break;

                String first = s.substring(0, i);
                String end = s.substring(i + 1);

                for(int j = 0; j < x; j++){
                   String tmp = end.charAt(0) + "";
                   first = first + tmp;
                }
                s = first + end;
                i = first.length() - 1;
            }
        }
        return s;
    }
}
