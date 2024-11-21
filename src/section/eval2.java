package section;

import acm.program.ConsoleProgram;

public class eval2 extends ConsoleProgram {


    //  5 + 9 - 333 + 37492 - 82342htaor+_(*30489oasrne9
    public void run() {
        String s = "11 + 2 * 3 - 8 * 2 / 2 ^ 2 * (1 + 2 * (3 + 6 - 3 * 3) / 10 + 1)"; //9

        String str = "(12 + 46) / 2 + 3 * 2 - 1"; // 34

        String res = evaluate(s);
        println(stringToInt(res));

    }

    private boolean isDigit(Character c) {
        return c >= '0' && c <= '9';
    }

    private Boolean isNum(String s) {
        if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {
            return true;
        }
        return false;
    }

    private String getLeftNum(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        String res = "";
        for (int i = 0; i < s.length(); i++) {
            if (!isDigit(s.charAt(i))) {
                break;
            }
            res = res + s.charAt(i);
        }
        return res;
    }

    private String removeLeftNum(String s) {
        String res = "";

        int i = 0;
        for (; i < s.length(); i++) {
            if (!isDigit(s.charAt(i))) {
                break;
            }
        }

        if (i >= s.length()) {
            return "";
        }

        res = s.substring(i);
        return res;
    }

    private String removeLastOperator(String s) {
        if (s.length() == 1) return "";
        return s.substring(1);
    }

    private Character getOperator(String s) {
        return s.charAt(0);
    }

    private String getRightNum(String s) {
        String res = "";

        for (int i = s.length()-1; i >= 0; i--) {
            Character c = s.charAt(i);
            if (!isDigit(c)) {
                break;
            }

            res = c + res;
        }

        return res;
    }

    private String removeRightNum(String s) {
        String res = "";
        int i;
        for (i = s.length() - 1; i >= 0; i--) {
            if (!isDigit(s.charAt(i))) {
                break;
            }
        }

        if (i < 0) {
            return "";
        }

        res = s.substring(0, i + 1);
        if(res.charAt(res.length()-1) == ' '){
            res = res.substring(0, res.length()-1);
        }
        return res;
    }

    private Integer stringToInt(String s) {
        //base case
        if (s.length() == 1) {
            return s.charAt(0) - '0';
        }

        //123
        //inductive step
        char lowestRank = s.charAt(s.length() - 1);
        int leftMost = lowestRank - '0';

        s = s.substring(0, s.length() - 1);

        int answerWithoutLastDigit = stringToInt(s);
        return answerWithoutLastDigit * 10 + leftMost;
    }

    private String intToString(int n) {
        if (n <= 9) {
            String res = "" + '0' + n;
            return res;
        }

        int lowestRank = n % 10;
        char leftmost = (char) ('0' + lowestRank);

        n = n / 10;

        String newS = intToString(n);
        if (newS.charAt(0) == '0') {
            newS = newS.substring(1);
        }
        newS += leftmost;
        return newS;
    }

    private String makeOperationOnTwoNumbers(String num1, String num2, Character op) {
        int n1 = stringToInt(num1);
        int n2 = stringToInt(num2);
        int res = switch (op) {
            case '+' -> n1 + n2;
            case '-' -> n1 - n2;
            case '*' -> n1 * n2;
            case '/' -> n1 / n2;
            case '^' -> (int) Math.pow(n1, n2);
            default -> 0;
        };

        return intToString(res);
    }

    private String correct(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (isDigit(c) || "+-*/^()".indexOf(c) >= 0) {
                res.append(c);
            }
        }
        return res.toString();
    }


    private Boolean greaterRank(Character op1, Character op2) {
        if (op2 == '^') return false;
        if (op1 == '^') return true;

        if (op2 == '*' || op2 == '/') return false;
        if (op1 == '*' || op1 == '/') return true;

        return false;
    }

    private String evaluate(String s) {
        String str = correct(s);

        String digits = "";
        String operators = "";

        while (!str.isEmpty()) {
            if (isNum(str)) {
                String curDigit = getLeftNum(str);
                str = removeLeftNum(str);

                if (operators.isEmpty()) {
                    digits = curDigit;
                } else {
                    char op = operators.charAt(operators.length() - 1);

                    if (op == '^') {
                        operators = operators.substring(0, operators.length() - 1);
                        String rightDigit = getRightNum(digits);
                        digits = removeRightNum(digits);

                        String newDigit = makeOperationOnTwoNumbers(rightDigit, curDigit, op);
                        digits = digits.isEmpty() ? newDigit : digits + " " + newDigit;
                    } else {
                        digits = digits.isEmpty() ? curDigit : digits + " " + curDigit;
                    }
                }
            } else {
                char curOp = getOperator(str);
                str = removeLastOperator(str);

                if (curOp == '(') {
                    operators += curOp;
                } else if (curOp == ')') {
                    while (operators.charAt(operators.length() - 1) != '(') {
                        String[] result = processTopOperator(digits, operators);
                        digits = result[0];
                        operators = result[1];
                    }
                    operators = operators.substring(0, operators.length() - 1); // Remove '('
                } else {
                    while (!operators.isEmpty() && !greaterRank(curOp, operators.charAt(operators.length() - 1))) {
                        if(operators.charAt(operators.length()-1) == '('){
                            break;
                        }
                        String[] result = processTopOperator(digits, operators);
                        digits = result[0];
                        operators = result[1];
                    }
                    operators += curOp;
                }
            }
        }

        while (!operators.isEmpty()) {
            String[] result = processTopOperator(digits, operators);
            digits = result[0];
            operators = result[1];
        }

        return digits;
    }

    private String[] processTopOperator(String digits, String operators) {
        if (operators.isEmpty() || digits.isEmpty()) return new String[]{digits, operators};

        char op = operators.charAt(operators.length() - 1);

        operators = operators.substring(0, operators.length() - 1);

        String num2 = getRightNum(digits);
        digits = removeRightNum(digits);
        String num1 = getRightNum(digits);
        digits = removeRightNum(digits);

        String result = makeOperationOnTwoNumbers(num1, num2, op);

        digits = digits.isEmpty() ? result : digits + " " + result;

        return new String[]{digits, operators};
    }


}


