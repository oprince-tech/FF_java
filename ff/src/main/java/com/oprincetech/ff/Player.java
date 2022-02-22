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
  Position position;

  static final Dictionary<Integer, String> POSITIONIDS = new Hashtable<Integer, String>();
  static final Dictionary<Integer, String> SLOTIDS = new Hashtable<Integer, String>();

  public Player(JSONObject entry) {
    Long long_slot_id = (Long) entry.get("lineupSlotId");
    slot_id = Math.toIntExact(long_slot_id);
    playerPoolEntryObj = (JSONObject) entry.get("playerPoolEntry");
    playerInfoObj = (JSONObject) playerPoolEntryObj.get("player");
    statsArray = (JSONArray) playerInfoObj.get("stats");
    playerId = (Long) entry.get("playerId");
  }

  static {
    POSITIONIDS.put(1, "QB");
    POSITIONIDS.put(2, "RB");
    POSITIONIDS.put(3, "WR");
    POSITIONIDS.put(4, "TE");
    POSITIONIDS.put(5, "K");
    POSITIONIDS.put(16, "DST");
    SLOTIDS.put(0, "QB");
    SLOTIDS.put(2, "RB");
    SLOTIDS.put(4, "WR");
    SLOTIDS.put(6, "TE");
    SLOTIDS.put(7, "FLX");
    SLOTIDS.put(16, "DST");
    SLOTIDS.put(17, "K");
    SLOTIDS.put(20, "B");
    SLOTIDS.put(21, "IR");
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
    pos = POSITIONIDS.get(int_pos_id);
    if (pos == "QB") {
      position = new QB();
    } else if (pos == "RB") {
      position = new RB();
    } else if (pos == "WR") {
      position = new WR();
    } else if (pos == "TE") {
      position = new TE();
    } else if (pos == "K") {
      position = new K();
    } else if (pos == "DST") {
      position = new DST();
    }
    position.statsArray = statsArray;
    position.generateGeneralStats();
    proj = position.longproj;
    score = position.longscore;
    position.generatePositionStats();
  }

  public void generateSlot() {
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
