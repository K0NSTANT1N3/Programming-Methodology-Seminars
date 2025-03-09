package section.football;

import acm.program.ConsoleProgram;
import exams.finaleExamProblem.exam2022_2023.football.Footballer;

import java.util.*;

public class Football extends ConsoleProgram implements Footballer {

    HashMap<String, ArrayList<String>> clubs;
    HashMap<String, ArrayList<String>> players;


    public Football() {
        clubs = new HashMap<>();
        players = new HashMap<>();
    }

    @Override
    public void addFootballPlayer(String club, String player) {
        clubs.putIfAbsent(club, new ArrayList<>());
        clubs.get(club).add(player);
        players.putIfAbsent(player, new ArrayList<>());
        players.get(player).add(club);
    }

    @Override
    public Iterator<String> getClubs(String player) {
        if (!players.containsKey(player)) return null;
        return players.get(player).iterator();
    }

    @Override
    public Iterator<String> getPlayers(String club, int n) {
        Set<String> cheaters = new HashSet<>();
        for (String player : players.keySet()) {
            if (players.get(player).size() > 3) {
                cheaters.add(player);
            }
        }
        Set<String> cheatersList = new HashSet<>();
        for (String cheater : cheaters) {
            for (String badClub : players.get(cheater)) {
                cheatersList.add(badClub);
            }
        }

        ArrayList<String> goodPlayers = new ArrayList<>();
        for (String player : players.keySet()) {
            boolean isGood = true;
            for (String curClub : players.get(player)) {
                if (cheatersList.contains(curClub)) {
                    isGood = false;
                    break;
                }
            }
            if (isGood) {
                goodPlayers.add(player);
            }
        }

        if (goodPlayers.size() == 0) {
            return null;
        }
        return goodPlayers.iterator();
    }
}
