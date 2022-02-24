package com.oprincetech.ff;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.json.simple.JSONObject;

public class App {

  private static int leagueId;
  private static Long longTeamId;
  private static int week;
  private static int season;

  public static void main(String[] args) {
    // parseArgs();
    int season = 2021;
    int leagueId = 131034;
    int teamId = 9;
    Long longTeamId = Long.valueOf(teamId);
    int week = 8;
    Boolean showMatchup = true;

    FileManager fileManager = new FileManager(leagueId, week, season);
    fileManager.generateFilenamePath();
    fileManager.loadFFJsonFile();

    JSONObject jsonData = fileManager.jsonData;

    League league = new League(jsonData, week, season);
    league.generateTeams();
    league.generateTeamInfo();
    league.generateSchedule();

    Team team = league.getTeamById(longTeamId);
    Team oppTeam;
    Matchup matchup;

    Roster roster = team.team_roster;
    roster.generatePlayers();
    roster.sortRosterBySlotId();

    if (showMatchup) {
      matchup = league.schedule.getCurrentMatchupByTeamId(teamId);
      if (teamId == matchup.homeTeamId) {
        oppTeam = league.getTeamById(matchup.awayTeamId);
      } else {
        oppTeam = league.getTeamById(matchup.homeTeamId);
      }
      Roster oppRoster = oppTeam.team_roster;
      oppRoster.generatePlayers();
      oppRoster.sortRosterBySlotId();

      matchup.printMatchupTeamInfo(team, oppTeam);
      matchup.printMatchupPlayers(team, oppTeam);
    } else {
      team.printTeamInfo();
      roster.printPlayers();
    }
    
 
    // Print to console

      // team.printTeamInfo();
      // team.printPlayers();
    
    // if (matchup) {
    //   Matchup currentMatchup = league.getCurrentMatchupById(teamId);
      // int opponentId = league.getOpponentById(teamId);
      // league.printTeamsInfo(teamId, opponentId);
      // league.printMatchup();
    // } else {
      // team.printTeamInfo();
      // roster.printPlayers();
    // }
  }

  public static void parseArgs() {
    Scanner argparse = new Scanner(System.in);
    try {
      System.out.print("League ID: ");
      leagueId = argparse.nextInt();
      System.out.print("Team ID: ");
      longTeamId = argparse.nextLong();
      System.out.print("Week: ");
      week = argparse.nextInt();
      System.out.print("Season: ");
      season = argparse.nextInt();
      argparse.close();
    } catch (InputMismatchException e) {
      throw e;
    } finally {
      // Always close scanner
      argparse.close();
    }
  }
}
