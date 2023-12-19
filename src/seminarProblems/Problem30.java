package seminarProblems;//reverse number

import acm.program.ConsoleProgram;

public class Problem30 extends ConsoleProgram {
    public void run() {
        int n = readInt();
        int ans = 0;
        while (n > 0) {
            ans = ans * 10 + n % 10;
            n /= 10;
        }
        print(ans);
    }
}
