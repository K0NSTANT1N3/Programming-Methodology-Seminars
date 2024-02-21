package exams.finaleExamProblem.exam2022_2023.football;

import java.util.Iterator;

public interface Footballer {
    public void addFootballPlayer(String club, String player);
    public Iterator<String> getClubs(String player);
    public Iterator<String> getPlayers(String club, int n);
}
