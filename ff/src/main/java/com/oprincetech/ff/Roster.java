package com.oprincetech.ff;

import java.util.ArrayList;
import java.util.Collections;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class Roster {

  Double appliedStatTotal;
  JSONObject entry;
  JSONArray entries;
  ArrayList<Player> players = new ArrayList<Player>();

  public Roster(JSONObject roster) {
    appliedStatTotal = (Double) roster.get("appliedStatTotal");
    entries = (JSONArray) roster.get("entries");
  }

  public void generatePlayers() {
    for (int i = 0; i < entries.size(); i++) {
      entry = (JSONObject) entries.get(i);
      Player player = new Player(entry);
      player.generatePlayerInfo();
      player.reKeySlotId();
      player.generatePosition();
      player.generateSlot();
      players.add(player);
    }
  }

  public void printRosterInfo() {
    System.out.println("ROSTER\n=============");
    System.out.println("appliedStatsTotal: " + appliedStatTotal);
    System.out.println("# Players: " + players.size() + "\n");
  }

  public void printPlayers() {
    System.out.println("Slot\tPos\tPlayer\t\t\tProj\tScore");
    System.out.println("=============================================");
    for (Player player : players) {
      player.printPlayerInfo();
    }
  }

  public void sortRosterBySlotId() {
    Collections.sort(players);
  }
}
