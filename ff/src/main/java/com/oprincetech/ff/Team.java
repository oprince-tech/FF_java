package com.oprincetech.ff;

import org.json.simple.JSONObject;

class Team {

  public String abbrev;
  Long rank;
  double playoffPct;
  JSONObject roster;
  Roster team_roster;
  Record team_record;
  JSONObject currentSimulationResults;
  Long tid;
  JSONObject team;

  public Team(JSONObject team) {
    this.team = team;
  }

  public void generateInfo() {
    tid = (Long) team.get("id");
    currentSimulationResults =
      (JSONObject) team.get("currentSimulationResults");
    rank = (Long) currentSimulationResults.get("rank");
    playoffPct = (double) currentSimulationResults.get("playoffPct") * 100;
    roster = (JSONObject) team.get("roster");
    abbrev = (String) team.get("abbrev");
    JSONObject recordJson = (JSONObject) currentSimulationResults.get(
      "modeRecord"
    );
    team_record = new Record(recordJson);
    generateRoster();
  }

  public void generateRoster() {
    team_roster = new Roster(roster);
  }

  public Long getTID() {
    return tid;
  }

  public void printTeamInfo() {
    System.out.println("TEAM\tRecord\tRank\tPO%");
    System.out.println(
      abbrev +
      "\t" +
      team_record.wins +
      "-" +
      team_record.losses +
      "-" +
      team_record.ties +
      "\t" +
      rank +
      "\t" +
      playoffPct
    );
  }
}
