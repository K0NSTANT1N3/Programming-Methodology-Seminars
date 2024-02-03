package exams.finaleExamProblem.madeUpProblems;

import java.util.ArrayList;

public class Sorsted {
    public boolean sorted(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); i++){
                swap(i, j, arr);

                for(int l = 0; l < arr.size(); l ++){
                    for (int r = l + 1; r < arr.size(); r++){
                        reverse(l, r, arr);

                        if(isSorted(arr)){
                            return true;
                        }
                        reverse(l, r, arr);

                    }
                }
                swap(i, j, arr);
            }
        }
        return false;
    }

    private boolean isSorted(ArrayList<Integer> arr) {
        for (int i = 1; i < arr.size(); i++){
            if(arr.get(i) < arr.get(i - 1))return false;
        }
        return true;
    }

    private void reverse(int l, int r, ArrayList<Integer> arr) {
        for (int i = l; i < (l + r) / 2; i++){
            swap(i, r - i + l, arr);
        }

    }

    private void swap (int i, int j, ArrayList<Integer> arr){
        int val = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, val);
    }
}
