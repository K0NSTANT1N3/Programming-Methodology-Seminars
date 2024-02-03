package exams.finaleExamProblem.final_mario;

import java.util.ArrayList;
import java.util.HashMap;

public class MergeImage {

    private boolean[][] pictureUnion(boolean[][] p1, boolean[][] p2) {
        HashMap<Integer, Integer> margin = rightMargine(p1);
        HashMap<Integer, Integer> border = leftBorder(p2);

        int cover = 0;
        for (int i = 0; i < p1[0].length; i++) {
            if (isPossible(margin, border, p1[0].length, i)) {
                cover = i;
            }
        }

        ArrayList<ArrayList<Boolean>> merged = merge(p1, p2, cover);

        boolean[][] res = listToArray(merged);

        return res;
    }

    private boolean[][] listToArray(ArrayList<ArrayList<Boolean>> merged){
        boolean[][] res = new boolean[merged.size()][merged.get(0).size()];

        for (int i = 0; i < merged.size(); i++) {
            for (int j = 0; j < merged.get(i).size(); j++) {
                res[i][j] = merged.get(i).get(j);
            }
        }
        return res;
    }


    private ArrayList<ArrayList<Boolean>> merge(boolean[][] p1, boolean[][] p2, int cover) {
        ArrayList<ArrayList<Boolean>> merged = new ArrayList<>();

        for (int i = 0; i < p1.length; i++) {
            ArrayList<Boolean> row = new ArrayList<>();
            for (int j = 0; j < p1[0].length; j++) {
                row.add(p1[i][j]);
            }
            merged.add(row);
        }

        for (int i = 0; i < p1.length; i++) {
            for (int j = 0; j < cover; j++) {
                if (p2[i][j]) {
                    merged.get(i).add(merged.get(i).size() - cover + j, true);
                }
            }
        }

        for (int i = 0; i < p2.length; i++) {
            ArrayList<Boolean> row = merged.get(i);
            for (int j = cover; j < p2[0].length; j++) {
                row.add(p2[i][j]);
            }
            merged.add(row);
        }
        return merged;
    }

    private HashMap<Integer, Integer> rightMargine(boolean[][] p) {
        HashMap<Integer, Integer> margin = new HashMap<>();
        int width = p[0].length;
        int height = p.length;

        for (int i = 0; i < height; i++) {
            int desired = -1;
            for (int j = width - 1; j >= 0; j--) {
                if (p[i][j]) {
                    desired = j + 1;
                    break;
                }
            }
            margin.put(i, desired);
        }
        return margin;
    }

    private HashMap<Integer, Integer> leftBorder(boolean[][] p) {
        HashMap<Integer, Integer> border = new HashMap<>();
        int width = p[0].length;
        int height = p.length;

        for (int i = 0; i < height; i++) {
            int desired = width;
            for (int j = 0; j < width; j++) {
                if (p[i][j]) {
                    desired = j;
                    break;
                }
            }
            border.put(i, desired);
        }
        return border;
    }

    private boolean isPossible(HashMap<Integer, Integer> margin, HashMap<Integer, Integer> boarder, int width, int cover) {
        boolean ans = true;
        for (int i = 0; i < margin.size(); i++) {
            if (margin.get(i) == width - cover + boarder.get(i)) {
                ans = false;
                break;
            }
        }
        return ans;
    }
}

