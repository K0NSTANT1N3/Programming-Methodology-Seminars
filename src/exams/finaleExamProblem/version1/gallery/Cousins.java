package exams.finaleExamProblem.version1.gallery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Cousins {

    private ArrayList<String> getCousins(String fileName, String name) {
        HashMap<String, String> sonFather = fileToMap(fileName);

        String father = sonFather.get(name);
        String grandpa = sonFather.get(father);

        HashSet<String> uncles = bringUncles(grandpa, sonFather);
        uncles.remove(father);

        return bringCousins(sonFather, uncles);
    }

    private ArrayList<String> bringCousins(HashMap<String, String> sonFather, HashSet<String> uncles) {
        ArrayList<String> cousins = new ArrayList<>();
        for (String uncle: uncles){
            for (String son : sonFather.keySet()){
                if (sonFather.get(son).equals(uncle)){
                    cousins.add(son);
                }
            }
        }
        return cousins;
    }

    private HashMap<String, String> fileToMap(String fileName) {
        HashMap<String, String> map = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                StringTokenizer token = new StringTokenizer(line);
                map.put(token.nextToken(), token.nextToken());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    private HashSet<String> bringUncles(String grandpa, HashMap<String, String> sonFather) {
        HashSet<String> uncles = new HashSet<>();

        for (String str : sonFather.keySet()) {
            if (sonFather.get(str).equals(grandpa)) {
                uncles.add(str);
            }
        }

        return uncles;
    }

}
