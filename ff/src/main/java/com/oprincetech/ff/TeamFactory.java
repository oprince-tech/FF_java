package com.oprincetech.ff;

import org.json.simple.JSONObject;

public interface TeamFactory {
  public Team create(JSONObject team);
}
