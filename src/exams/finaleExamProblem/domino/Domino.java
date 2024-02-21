package exams.finaleExamProblem.domino;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

import java.util.HashSet;

public class Domino extends ConsoleProgram {

    private acm.util.RandomGenerator rand = RandomGenerator.getInstance();


    public double simulateDomino(int n) {
        int winnerCase = 0;
        for (int j = 0; j < n; j++) {
            HashSet<Integer> deck = new HashSet<>();
            while (deck.size() <= 7) {
                deck.add(rand.nextInt(0, 28));
            }
            if (winnerDeck(deck)) winnerCase++;
        }
        return (double) winnerCase / n;
    }

    private boolean winnerDeck(HashSet<Integer> deck) {
        int couple = 0;
        for (int rock : deck) {
            if (rock >= 1 && rock <= 7) {
                couple++;
            }
        }
        return couple >= 5;
    }
}
