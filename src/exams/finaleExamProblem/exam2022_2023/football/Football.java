package exams.finaleExamProblem.exam2022_2023.football;

import acm.program.ConsoleProgram;

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
        if (players.get(player) == null) return null;

        return players.get(player).iterator();
    }

    @Override
    public Iterator<String> getPlayers(String club, int n) {
        ArrayList<String> traitorPlayers = new ArrayList<>();
        for(String player : players.keySet()){//find traitor players
            ArrayList<String> playersClub = players.get(player);
            if (playersClub.size() > n)traitorPlayers.add(player);
        }

        Set<String> traitorClubs = new HashSet<>();
        for(String player : traitorPlayers){//find traitor clubs
            ArrayList<String> traitorCub = players.get(player);
            traitorClubs.addAll(traitorCub);
        }

        Set<String> goodFootballer = new HashSet<>();
        for(String player : clubs.get(club)){//find worthy players
            boolean worthy = true;
            for (String clb : players.get(player)){
                if(traitorClubs.contains(clb)){
                    worthy = false;
                }
            }
            if(worthy){
                goodFootballer.add(player);
            }
        }

        if (goodFootballer.isEmpty())return null;

        return goodFootballer.iterator();
    }
}
