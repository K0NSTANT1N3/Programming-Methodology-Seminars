package seminarProblems;

import acm.program.ConsoleProgram;

import java.util.ArrayList;
import java.util.Arrays;

public class problem57 extends ConsoleProgram {
    public void run() {
        println(isAnagram(readLine(), readLine()));
    }

    private int[] gepFrequencies(String str) {
        int[] frequencies = new int[26];
        for (int i = 0; i < str.length(); i++) {
            frequencies[str.charAt(i) - 'a']++;
        }

        return frequencies;
    }

    private boolean isAnagram(String first, String second) {
        return Arrays.equals(gepFrequencies(first), gepFrequencies(second));
    }
}
