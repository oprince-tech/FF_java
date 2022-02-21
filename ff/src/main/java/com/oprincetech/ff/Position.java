package com.oprincetech.ff;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

abstract class Position {

  JSONArray statsArray;
  Double games_played;
  Double fpts_avg;
  Double fpts_total;
  JSONObject stats_listings;
  Double score;
  Double proj;
  Long longproj;
  Long longscore;

  Position() {}

  public abstract void generatePositionStats();

  public void generateGeneralStats() {
    for (int i = 0; i < statsArray.size(); i++) {
      JSONObject statsObj = (JSONObject) statsArray.get(i);
      String statsObjId = (String) statsObj.get("id");
      Long statsObjScoringPeriod = (Long) statsObj.get("scoringPeriodId");
      if (statsObjId.equals("00" + League.querySeason)) {
        stats_listings = (JSONObject) statsObj.get("stats");
        games_played = (Double) stats_listings.get("210");
        fpts_avg = (Double) statsObj.get("appliedAverage");
        fpts_total = (Double) statsObj.get("appliedTotal");
      }
      if (statsObjScoringPeriod == League.queryWeek) {
        Long statSourceId = (Long) statsObj.get("statSourceId");
        if (statSourceId == 0) {
          score = (Double) statsObj.get("appliedTotal");
          longscore = (Long) Math.round(score);
        } else if (statSourceId == 1) {
          if (statsObj.containsKey("appliedTotal")) {
            proj = (Double) statsObj.get("appliedTotal");
            longproj = (Long) Math.round(proj);
          }
        }
      }
    }
  }
}
