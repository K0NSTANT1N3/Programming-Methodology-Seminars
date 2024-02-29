package exams.finaleExamProblem.version1.final_exam;

import java.util.ArrayList;
import java.util.Collections;

public class SumNumbers {

    private int[] addBigInteger(int[] a, int[] b) {
        ArrayList<Integer> c = new ArrayList<>();

        ArrayList<Integer> arr = a.length >= b.length ?
                arrToList(a, a.length - b.length) :
                arrToList(b, b.length - a.length);
        ArrayList<Integer> brr = a.length < b.length ?
                arrToList(a, 0) : arrToList(b, 0);

        Collections.reverse(arr);
        Collections.reverse(brr);

        int incr = 0;
        for (int i = 0; i < arr.size(); i++) {
            int sm = arr.get(i) + brr.get(i);
            c.add(sm % 10 + incr);
            incr = sm / 10;
        }
        if(incr > 0)c.add(incr);

        Collections.reverse(c);
        int[] ans = new int[c.size()];
        for(int i = 0; i < ans.length; i++){
            ans[i] = c.get(i);
        }
        return ans;
    }

    private ArrayList<Integer> arrToList(int[] a, int odd) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < odd; i++) {
            arr.add(0);
        }
        for (int j : a) {
            arr.add(j);
        }

        return arr;
    }
}
