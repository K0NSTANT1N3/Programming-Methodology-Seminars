package seminarProblems;

import acm.program.ConsoleProgram;

import java.util.HashMap;
import java.util.HashSet;

public class Problem63 extends ConsoleProgram {
    public void run() {
        HashMap<String, HashSet<String>> followers = new HashMap<>();

        String follower = readLine();
        while (!follower.isEmpty()) {
            String followed = readLine();
            followers.putIfAbsent(followed, new HashSet<String>());
            followers.get(followed).add(follower);
            follower = readLine();
        }

        int number = 0;
        String top;
        for(String person: followers.keySet()){

        }
    }
}
