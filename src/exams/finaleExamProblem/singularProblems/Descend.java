package exams.finaleExamProblem.singularProblems;

public class Descend {

    public int getMaxDescend(int[][] mountains){
        int res = 0;
        for(int i = 0; i < mountains.length; i++){
            for(int j = 0; j < mountains[i].length; j++){
                res = Math.max(res, findDescend(i, j, mountains));
            }
        }
        return res;
    }

    private int findDescend(int x, int y, int[][] mountains){
        int max = 0;
        for(int i = x; i < mountains.length; i++){
            if(mountains[i][y] > mountains[x][y])break;
            max = Math.max(max, mountains[x][y] - mountains[i][y]);
        }
        for(int i = x; i >= 0; i--){
            if(mountains[i][y] > mountains[x][y])break;
            max = Math.max(max, mountains[x][y] - mountains[i][y]);
        }
        for(int i = y; i < mountains[x].length; i++){
            if(mountains[x][i] > mountains[x][y])break;
            max = Math.max(max, mountains[x][y] - mountains[x][i]);
        }
        for(int i = y; i >= 0; i--){
            if(mountains[x][i] > mountains[x][y])break;
            max = Math.max(max, mountains[x][y] - mountains[x][i]);
        }
        return max;
    }
}
