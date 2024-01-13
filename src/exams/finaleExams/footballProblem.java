//package exams.finaleExams;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//
//
//public class footballProblem {
//
//    public Iterator<String> getClubs(String player) {
//
//    }
//
//    public Iterator<String> getPlayers(String club, int n) {
//        List<String> players = new ArrayList<>();
//        for (String player : clubPlayer.get(club)) {
//            if (isGoodPlayer(player)) {
//                players.add(player);
//            }
//        }
//        while (players.size() > n) {
//            String worstPlayer = players.get(0);
//            for (String player : players) {
//                if (playerClub.get(player).size() < playerClub.get(worstPlayer).size()) {
//                    worstPlayer = player;
//                }
//            }
//            players.remove(worstPlayer);
//        }
//        return players.iterator();
//    }
//
//    private boolean isGoodPlayer(String player) {
//        for (String club : playerClub.get(player)) {
//            for (String teammate: clubPlayer.get(club)) {
//                if(playerClub.getSize() > 3){
//                    return false
//
//                }
//            }
//        }
//        return false;
//    }
//
//}
