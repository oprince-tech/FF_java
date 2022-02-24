package com.oprincetech.ff;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class League {

  JSONArray scheduleJson;
  JSONArray teamsJson;
  JSONObject teamJson;
  Schedule schedule;
  Long leagueId;
  Long seasonId;
  Long scoringPeriodId;
  Long opponentTeamId;
  ArrayList<Team> teams = new ArrayList<Team>();
  static int queryWeek;
  static int querySeason;

  public League(JSONObject jsonData, int week, int season) {
    queryWeek = week;
    querySeason = season;
    leagueId = (Long) jsonData.get("id");
    seasonId = (Long) jsonData.get("seasonId");
    scoringPeriodId = (Long) jsonData.get("scoringPeriodId");
    scheduleJson = (JSONArray) jsonData.get("schedule");
    teamsJson = (JSONArray) jsonData.get("teams");
  }

  public void generateSchedule() {
    schedule = new Schedule(scheduleJson);
    schedule.generateMatchups();
  }

  public void generateTeams() {
    try {
      for (int i = 0; i < teamsJson.size(); i++) {
        teamJson = (JSONObject) teamsJson.get(i);
        Team team = new Team(teamJson);
        teams.add(team);
      }
    } catch (Exception e) {
      throw e;
    }
  }

  public void generateTeamInfo() {
    for (Team team : teams) {
      team.generateInfo();
    }
  }

  public List<Team> getTeams() {
    return teams;
  }

  public Team getTeamById(Long tid) {
    Optional<Team> teamById = teams
      .stream()
      .filter(t -> tid.equals(t.getTID()))
      .findFirst();
    if (teamById.isPresent()) {
      Team team = teamById.get();
      return team;
    } else {
      return null;
    }
  }
}
