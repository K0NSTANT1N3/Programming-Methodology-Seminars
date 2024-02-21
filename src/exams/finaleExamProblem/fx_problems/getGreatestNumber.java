package exams.finaleExamProblem.fx_problems;

import acm.program.ConsoleProgram;

import java.util.ArrayList;
import java.util.Collections;

public class getGreatestNumber extends ConsoleProgram {

    public void run() {

        int[] arr = new int[readInt()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = readInt();
        }
        print(greatNum(arr));
    }

    public long greatNum(int[] arr) {
        ArrayList<String> strList = intToString(arr);

        Collections.sort(strList, (a, b) -> (b + a).compareTo(a + b));

        int ans = 0;
        for (int i = 0; i < strList.size(); i++) {
            int zeroNum = 1;
            int x = Integer.parseInt(strList.get(i));
            for (int j = x; j > 0; j /= 10) {
                zeroNum *= 10;
            }

            ans *= zeroNum;
            ans += x;
        }

        return ans;
    }

    private ArrayList<String> intToString(int[] arr) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i] + "");
        }
        return list;
    }

}
