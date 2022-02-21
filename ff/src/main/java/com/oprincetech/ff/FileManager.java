package com.oprincetech.ff;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class FileManager {

  String absPath;
  JSONObject jsonData;
  String filename;
  String workingDirectory;
  final String dataDir;
  String ext;
  String leagueIdString;
  String weekString;
  String seasonString;

  public FileManager(int leagueId, int week, int season) {
    try {
      workingDirectory = System.getProperty("user.dir");
      dataDir = "/data/";
      ext = ".json";
      leagueIdString = Integer.toString(leagueId);
      weekString = Integer.toString(week);
      seasonString = Integer.toString(season);
    } catch (SecurityException e) {
      throw e;
    }
  }

  public void generateFilenamePath() {
    try {
      filename =
        "FF_" + seasonString + "_wk" + weekString + "_" + leagueIdString + ext;
      absPath = workingDirectory + dataDir + filename;
    } catch (Exception e) {
      throw e;
    }
  }

  public void loadFFJsonFile() {
    try {
      System.out.println("Loading " + absPath);
      FileReader fileReader = new FileReader(absPath);
      JSONParser parser = new JSONParser();
      jsonData = (JSONObject) parser.parse(fileReader);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
}
