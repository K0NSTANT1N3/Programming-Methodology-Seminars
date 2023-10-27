import acm.program.ConsoleProgram;

public class Problem29 extends ConsoleProgram {
    public void run() {
        int n = readInt();
        int ans = 1, helper = 0;

        for (int i = 2; i < n; i++) {
            int tmp = ans;
            ans = helper + ans;
            helper = tmp;
        }
        println(ans);
    }
}