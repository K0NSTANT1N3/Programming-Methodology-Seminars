package section.dominofinal;

// 7530 + 942 - 2984 + 284 * i32 / 3894

import acm.program.ConsoleProgram;

import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class sum extends ConsoleProgram {

    public void run() {
        String str = "93 + 7 + 3 + 3 - 9 - 1";

        int ans = eval(str);
        println(ans);
    }

    private int eval(String str) {
        StringTokenizer token = new StringTokenizer(str, " ");
        int sum = 0;
        String firstNumber = token.nextToken();
        sum = parseInt(firstNumber);
        while(token.hasMoreTokens()){
            String curOp = token.nextToken();
            String curNum = token.nextToken();
            if(curOp.equals("+")) {
                sum += parseInt(curNum);
            }
            else if(curOp.equals("-")){
                sum -= parseInt(curNum);
            }
        }
        return sum;
    }

}

