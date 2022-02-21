package com.oprincetech.ff;

import java.lang.Override;
import java.util.Dictionary;
import java.util.Hashtable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class Player implements Comparable<Player> {

  Long playerId;
  String injuryStatus;
  int slot_id;
  Long pos_id;
  String pos;
  String slot;
  String lastName;
  String firstName;
  Long proj;
  Long score;
  JSONObject playerInfoObj;
  JSONObject playerPoolEntryObj;
  JSONArray statsArray;
  // Position position;

  Dictionary<Integer, String> POSITIONIDS = new Hashtable<Integer, String>();
  Dictionary<Integer, String> SLOTIDS = new Hashtable<Integer, String>();

  public Player() {}

  public Player(JSONObject entry) {
    Long long_slot_id = (Long) entry.get("lineupSlotId");
    slot_id = Math.toIntExact(long_slot_id);
    playerPoolEntryObj = (JSONObject) entry.get("playerPoolEntry");
    playerInfoObj = (JSONObject) playerPoolEntryObj.get("player");
    statsArray = (JSONArray) playerInfoObj.get("stats");
    playerId = (Long) entry.get("playerId");
    generatePlayerInfo();
    reKeySlotId();
    generatePosition();
    generateSlot();
  }

  public void reKeySlotId() {
    if (slot_id == 23) {
      this.slot_id = 7;
    }
  }

  public void generatePlayerInfo() {
    injuryStatus = (String) playerInfoObj.get("injuryStatus");
    firstName = (String) playerInfoObj.get("firstName");
    lastName = (String) playerInfoObj.get("lastName");
    pos_id = (Long) playerInfoObj.get("defaultPositionId");
  }

  public void generatePosition() {
    int int_pos_id = Math.toIntExact(pos_id);
    POSITIONIDS.put(1, "QB");
    POSITIONIDS.put(2, "RB");
    POSITIONIDS.put(3, "WR");
    POSITIONIDS.put(4, "TE");
    POSITIONIDS.put(5, "K");
    POSITIONIDS.put(16, "DST");
    pos = POSITIONIDS.get(int_pos_id);
    if (pos == "QB") {
      Position position = new QB();
      position.statsArray = statsArray;
      position.generateGeneralStats();
      proj = position.longproj;
      score = position.longscore;
      QB qb = (QB) position;
      qb.generatePositionStats();
    } else if (pos == "RB") {
      Position position = new RB();
      position.statsArray = statsArray;
      position.generateGeneralStats();
      proj = position.longproj;
      score = position.longscore;
      RB rb = (RB) position;
      rb.generatePositionStats();
    } else if (pos == "WR") {
      Position position = new WR();
      position.statsArray = statsArray;
      position.generateGeneralStats();
      proj = position.longproj;
      score = position.longscore;
      WR wr = (WR) position;
      wr.generatePositionStats();
    } else if (pos == "TE") {
      Position position = new TE();
      position.statsArray = statsArray;
      position.generateGeneralStats();
      proj = position.longproj;
      score = position.longscore;
      TE te = (TE) position;
      te.generatePositionStats();
    } else if (pos == "K") {
      Position position = new K();
      position.statsArray = statsArray;
      position.generateGeneralStats();
      proj = position.longproj;
      score = position.longscore;
      K k = (K) position;
      k.generatePositionStats();
    } else if (pos == "DST") {
      Position position = new DST();
      position.statsArray = statsArray;
      position.generateGeneralStats();
      proj = position.longproj;
      score = position.longscore;
      DST dst = (DST) position;
      dst.generatePositionStats();
    }
  }

  public void generateSlot() {
    SLOTIDS.put(0, "QB");
    SLOTIDS.put(2, "RB");
    SLOTIDS.put(4, "WR");
    SLOTIDS.put(6, "TE");
    SLOTIDS.put(7, "FLX");
    SLOTIDS.put(16, "DST");
    SLOTIDS.put(17, "K");
    SLOTIDS.put(20, "B");
    SLOTIDS.put(21, "IR");
    slot = SLOTIDS.get(slot_id);
  }

  // Comparse and sort by Position ID
  @Override
  public int compareTo(Player obj) {
    return this.slot_id - obj.slot_id;
  }

  public void printPlayerInfo() {
    System.out.println(
      slot + "\t" + pos + "\t" + lastName + "   \t\t" + proj + "\t" + score
    );
  }
}
