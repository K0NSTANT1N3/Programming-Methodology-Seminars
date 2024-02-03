package exams.finaleExamProblem.madeUpProblems;

import acm.program.ConsoleProgram;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayMultiply extends ConsoleProgram {

    public ArrayList<Integer> multiply(ArrayList<Integer> a, ArrayList<Integer> b) {
        Collections.reverse(a);
        Collections.reverse(b);
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> counter = new ArrayList<>();
        ArrayList<Integer> one = new ArrayList<>();
        one.add(1);

        while (!counter.equals(b)){
            result = sum(result, a);
            counter = sum(counter, one);
        }

        Collections.reverse(a);
        Collections.reverse(b);
        Collections.reverse(result);
        return result;
    }

    private ArrayList<Integer> sum(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> result = new ArrayList<>();
        int r = 0;
        for (int i = 0; i < a.size() || i < b.size(); i++) {
            int da = 0;
            if (i < a.size()){
                da = a.get(i);
            }

            int db = 0;
            if (i < b.size()){
                db = b.get(i);
            }

            int num = da + db + r;
            result.add(num % 10);
            r = num / 10;
        }
        if (r > 0){
            result.add(r);
        }

        return result;
    }
}
