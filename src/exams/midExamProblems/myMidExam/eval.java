package exams.midExamProblems.myMidExam;

import acm.program.ConsoleProgram;

import java.util.Stack;

public class eval extends ConsoleProgram {

    @Override
    public void run() {

    }

    private int eval(String st) {

        int ans = 0;
        String curPart = "";
        char operation = '0';
        for (int i = 0; i < st.length(); i++) {
            char curChar = st.charAt(i);
            if (curChar != ' ') {
                curPart = curPart + curChar;
            } else {
                if (curPart.equals("+")) {
                    operation = '+';
                } else if (curPart.equals("-")) {
                    operation = '-';
                } else {
                    int intPart = Integer.parseInt(curPart);
                    if (operation == '0') {
                        ans += intPart;
                    } else if (operation == '-') {
                        ans -= intPart;
                    } else if (operation == '+') {
                        ans += intPart;
                    }
                }
                curPart = "";
            }
        }
        return ans;
    }

    private String parseString(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) >= '0' && s.charAt(i) <= '9') || s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/' || s.charAt(i) == '(' || s.charAt(i) == ')') {
                res += s.charAt(i);
            }
        }
        return res;
    }

    private boolean isNum(String s){
        return true;
    }

    private String getNum(String s) {
        String res = "";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' || s.charAt(i) <= '9') {
                res += s.charAt(i);
            }
        }

        return res;
    }

    private String getOper(String s) {
        String res = "";
        String P = "";

        if (s.charAt(0) <= '0' && s.charAt(0) >= '9') {
            res += s.charAt(0);
        }

        return res;
    }

    private int evaluate(String s) {
        String str = parseString(s);

        Stack<String> nums;
        Stack<Character> operand;

        return 0;
    }

}


