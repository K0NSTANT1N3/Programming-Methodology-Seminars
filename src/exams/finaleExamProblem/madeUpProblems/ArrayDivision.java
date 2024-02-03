package exams.finaleExamProblem.madeUpProblems;

import acm.program.ConsoleProgram;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayDivision extends ConsoleProgram {

    public void run() {
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();

        int na = readInt();
        for (int i = 0; i < na; i++) {
            a.add(readInt());
        }

        int nb = readInt();
        for (int i = 0; i < nb; i++) {
            b.add(readInt());
        }

        print(divideArray(a, b));
    }

    private ArrayList<Integer> divideArray(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayList<Integer> copyOfA = new ArrayList<>(a);

        while (copyOfA.size() > 0) {
            copyOfA = subtractArray(copyOfA, b);
            increment(ans);
        }
        return ans;
    }


    private ArrayList<Integer> subtractArray(ArrayList<Integer> a, ArrayList<Integer> b) {
        Collections.reverse(a);
        Collections.reverse(b);

        ArrayList<Integer> res = new ArrayList<>();

        int r = 0;
        for (int i = 0; i < a.size(); i++) {
            int minuend = a.get(i);
            int subtractor = (i >= b.size()) ? 0 : b.get(i);

            int subtracted = minuend - subtractor - r;
            if (subtracted < 0) {
                subtracted += 10;
                r = 1;
            }

            res.add(subtracted);
        }

        Collections.reverse(res);
        while (!res.isEmpty() && res.get(0) == 0) {
            res.remove(0);
        }

        return res;
    }

    private void increment(ArrayList<Integer> a) {
        Collections.reverse(a);

        int i = 0;
        for (; i < a.size() && a.get(i) == 9; i++) {
            a.set(i, 0);
        }
        if (i >= a.size()) {
            a.add(1);
        } else if (a.get(i) != 9) {
            a.set(i, a.get(i) + 1);
        }
        Collections.reverse(a);
    }
}
