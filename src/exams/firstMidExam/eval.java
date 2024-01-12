package exams.firstMidExam;

import acm.program.ConsoleProgram;

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


}

