package com.oprincetech.ff;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Schedule {
    private JSONArray scheduleJson;
    ArrayList<Matchup> matchups = new ArrayList<Matchup>();

    public Schedule(JSONArray scheduleJson) {
        this.scheduleJson = scheduleJson;
    }

    public void generateMatchups() {
        for (int i = 0; i < scheduleJson.size(); i++) {
            JSONObject matchupJson = (JSONObject) scheduleJson.get(i);
            Matchup matchup = new Matchup(matchupJson);
            matchup.generateMatchupInfo();
            matchups.add(matchup);
        }
    }

    public Matchup getCurrentMatchupByTeamId(int teamId) {
        Long teamIdLong = Long.valueOf(teamId);
        Long queryWeekLong = Long.valueOf(League.queryWeek);
        Predicate<Matchup> teamIdMatch = e -> e.getHomeTeamId() == teamId || e.getAwayTeamId() == teamId;
        Predicate<Matchup> currentMatchupPeriod = e -> e.getMatchupPeriodId() == League.queryWeek; 
  
        Optional<Matchup> matchupByTeamId = matchups
            .stream()
            .filter(currentMatchupPeriod)
            .filter(teamIdMatch)
            .findFirst();

        if (matchupByTeamId.isPresent()) {
            Matchup matchup = matchupByTeamId.get();
            return matchup;
        } else {
            return null;
        }
    }
}
