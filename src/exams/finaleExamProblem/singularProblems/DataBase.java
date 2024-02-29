package exams.finaleExamProblem.singularProblems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataBase {
    Set<String> info;
    Set<Site> data;

    public DataBase(String filename){
        info = new HashSet<>();
        try{
            BufferedReader buf = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = buf.readLine()) != null) {
                info.add(line);
            }
        }catch (IOException e){
            System.out.println("problem");
        }
    }

    public Site getSiteByURL(String url){

    }
    public List<Site> getSiteByKeyWord(String keyWord){

    }
}
