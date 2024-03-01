package exams.finaleExamProblem.singularProblems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataBase {
    Set<String> info;
    Set<Site> data;

    public DataBase(String filename) {
        info = new HashSet<>();
        try {
            BufferedReader buf = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = buf.readLine()) != null) {
                info.add(line);
                Site site = new Site(line);
                data.add(site);
            }
        } catch (IOException e) {
            System.out.println("problem");
        }
    }

    public Site getSiteByURL(String url) {
        for (Site site : data) {
            if (site.url.equals(url)) {
                return site;
            }
        }
        return null;
    }

    public List<Site> getSiteByKeyWord(String keyWord) {
        List<Site> lst = new ArrayList<>();
        for (Site site : data) {
            Iterator<String> itr = site.getKeywords();
            while(itr.hasNext()){
                String word = itr.next();
                if(word.equals(keyWord)){
                    lst.add(site);
                }
            }
        }

        return lst;
    }
}
