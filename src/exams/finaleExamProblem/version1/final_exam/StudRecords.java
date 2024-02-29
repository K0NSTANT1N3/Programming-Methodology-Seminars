package exams.finaleExamProblem.version1.final_exam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StudRecords {

    ArrayList<ArrayList<String>> data;

    public StudRecords(String filename) {
        data = new ArrayList<>();

        try {
            BufferedReader buf = new BufferedReader(new FileReader(filename));
            for (int j = 0; ; j++) {
                String line = buf.readLine();
                if (line == null) return;

                data.add(new ArrayList<>());
                StringTokenizer token = new StringTokenizer(line, ", ");
                while (token.hasMoreTokens()) {
                    String couplet = token.nextToken();
                    StringTokenizer tkn = new StringTokenizer(couplet, ": ");
                    tkn.nextToken();
                    String val = tkn.nextToken();
                    data.get(j).add(val);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getStudentMarkInCourse(String stud, String course) {
        for (ArrayList<String> datum : data) {
            if (datum.get(0).equals(stud) && datum.get(1).equals(course)) {
                return Integer.parseInt(datum.get(2));
            }
        }
        return -1;
    }

    public Iterator<String> getStudentsWithMark(String course, int mark) {
        HashSet<String> stud = new HashSet<>();
        for (ArrayList<String> datum : data) {
            if (datum.get(1).equals(course) && datum.get(2).equals(mark + "")) {
                stud.add(datum.get(0));
            }
        }
        return stud.iterator();
    }

    public Map<String, ArrayList<String>> getCoursesWithMark(int mark){
        Map<String, ArrayList<String>> couplet = new HashMap<>();
        for(ArrayList<String> itr : data){
            if(itr.get(2).equals(mark + "")){
                couplet.putIfAbsent(itr.get(0), new ArrayList<>());
                ArrayList<String> str = couplet.get(itr.get(0));
                if (str != null)str.add(itr.get(1));
            }
        }
        return couplet;
    }
}
