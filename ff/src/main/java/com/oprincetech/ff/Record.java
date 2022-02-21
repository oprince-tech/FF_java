package com.oprincetech.ff;

import org.json.simple.JSONObject;

class Record {

  Long wins;
  Long losses;
  Long ties;

  public Record(JSONObject record) {
    wins = (Long) record.get("wins");
    losses = (Long) record.get("losses");
    ties = (Long) record.get("ties");
  }
}
