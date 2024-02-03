package exams.finaleExamProblem.version1;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

import java.util.HashSet;
import java.util.Set;

public class JokerProbabilitySimulator extends ConsoleProgram {

    public void run(){
        print(simulateJoker(readInt()));
    }

    private double simulateJoker(int n) {
        RandomGenerator rgen = RandomGenerator.getInstance();
        Set<Integer> cardDec = new HashSet<>();
        Set<Integer> player1 = new HashSet<>();

        int jokerYes = 0;
        int jokerNot = 0;

        for (int j = 0; j < n; j++) {
            cardDec.clear();
            player1.clear();

            for (int i = 0; i < 36; i++) cardDec.add(i);

            for (int i = 0; i < 9; ) {
                for (int itr : cardDec) {
                    if (rgen.nextInt(0, 36) == 1) {
                        player1.add(itr);
                        i++;
                    }
                    if (i >= 9) break;
                }
                for (int itr : player1) {
                    if (cardDec.contains(itr)) {
                        cardDec.remove(itr);
                    }
                }
            }

            if (player1.contains(1) && player1.contains(2)){
                jokerYes++;
            }else {
                jokerNot++;
            }
        }

        double freq =  (double) jokerYes / (jokerNot + jokerYes);
        return freq;
    }

}
