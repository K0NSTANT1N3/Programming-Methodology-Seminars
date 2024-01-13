package exams.finaleExams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Enemy {
    private boolean enemy(String filename, String name) {
        Map<String, Collection<String>> friends = new HashMap<>();

        try {
            BufferedReader rd = new BufferedReader(
                    new FileReader(filename));
            while(true){
                String line = rd.readLine();
                if(line == null) break;
                StringTokenizer tokenizer = new StringTokenizer(line);
                String a = tokenizer.nextToken();
                String b = tokenizer.nextToken();
                friends.putIfAbsent(a, new HashSet<>());
                friends.putIfAbsent(b, new HashSet<>());
                friends.get(a).add(b);
                friends.get(b).add(a);
            }
        }catch (IOException e){

        }

        Collection <String> enemiesOfEnemies = new HashSet<>();


        return false;
    }


    private boolean getEnemies(String name, Map<String, Collection<String>> friends) {
        Collection<String> enemies = new HashSet<>();
        for (String enemy : friends.keySet()) {
            if (!friends.get(name).contains(enemy)) {
                enemies.add(enemy);
            }
        }
        return false;
    }
}
