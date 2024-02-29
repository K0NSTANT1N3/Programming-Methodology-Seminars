package exams.finaleExamProblem.singularProblems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Site {
    ArrayList<String> keyword;
    String url;
    String contest;

    public Site(String line) {
        StringTokenizer tkn = new StringTokenizer(line, "] ");

        String junk = tkn.nextToken();
        url = junk.substring(1);

        junk = tkn.nextToken();
        contest = junk.substring(1);

        junk = tkn.nextToken();
        junk = junk.substring(1, junk.length() - 1);
        tkn = new StringTokenizer(junk, ", ");

        keyword = new ArrayList<>();
        while (tkn.hasMoreTokens()) {
            keyword.add(tkn.nextToken());
        }
    }

    public String getURL() {
        return this.url;
    }

    public String getContent() {
        return this.contest;
    }

    public Iterator<String> getKeywords() {
        return keyword.iterator();
    }
}
