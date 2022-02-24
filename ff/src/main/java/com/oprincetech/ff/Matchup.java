package com.oprincetech.ff;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.simple.JSONObject;

public class Matchup {
    private JSONObject matchupJson;
    Long matchupPeriodId;
    String winner;
    Long homeTeamId;
    Long awayTeamId;

    public Matchup(JSONObject matchupJson) {
        this.matchupJson = matchupJson;
    }

    public void generateMatchupInfo() {
        matchupPeriodId = (Long) matchupJson.get("matchupPeriodId");
        winner = (String) matchupJson.get("winner");
        for (int i = 0; i < matchupJson.size(); i++) {
            JSONObject awayTeam = (JSONObject) matchupJson.get("away");
            awayTeamId = (Long) awayTeam.get("teamId");
            JSONObject homeTeam = (JSONObject) matchupJson.get("home");
            homeTeamId = (Long) homeTeam.get("teamId");
        }
    }

    public Long getHomeTeamId() {
        return homeTeamId;
    }
    public Long getAwayTeamId() {
        return awayTeamId;
    }
    public Long getMatchupPeriodId() {
        return matchupPeriodId;
    }

    public void printMatchupTeamInfo(Team team, Team oppteam) {
        System.out.println("TEAM\tRecord\tRank\tPO%\t\t\tTEAM\tRecord\tRank\tPO%");
        System.out.println(
        team.abbrev +
        "\t" +
        team.team_record.wins +
        "-" +
        team.team_record.losses +
        "-" +
        team.team_record.ties +
        "\t" +
        team.rank +
        "\t" +
        team.playoffPct
        );
       
    }
    public void printMatchupPlayers(Team team, Team oppteam) { 
        ArrayList<Player> team_list = team.team_roster.players;
        ArrayList<Player> oppteam_list = oppteam.team_roster.players;
        List<Map.Entry<Player, Player>> zipped = new ArrayList<>(team_list.size());
        if (team_list.size() == oppteam_list.size()) {
            for (int i = 0; i < team_list.size(); i++) {
                zipped.add(new AbstractMap.SimpleEntry<Player, Player>(team_list.get(i), oppteam_list.get(i)));
            }
        }

        for (Map.Entry<Player, Player> entry : zipped) {
            Player myPlayer = entry.getKey();
            Player oppPlayer = entry.getValue();
            String matchup_player_line = String.format("%s\t%s\t%-10s\t%s\t%s\t%s\t%s\t%-10s\t%s\t%s", myPlayer.slot, myPlayer.pos, myPlayer.lastName, myPlayer.proj, myPlayer.score, oppPlayer.slot, oppPlayer.pos, oppPlayer.lastName, oppPlayer.proj, oppPlayer.score);
            System.out.println(matchup_player_line);

        }
    }
}
