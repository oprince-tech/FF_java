package com.oprincetech.ff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

@RunWith(PowerMockRunner.class)
@PrepareForTest({League.class})
class LeagueTest {

  private JSONObject testObjFull;
  private JSONArray testSchedule;
  private JSONArray testTeams;
  private League league;

  @BeforeEach
  public void setUp() {
    // JSONObject jsonObj = new JSONObject();
    // league = new League(jsonObj, 1, 1);
    testObjFull = new JSONObject();
    testSchedule = new JSONArray();
    testTeams = new JSONArray();
    JSONObject mockTeam = new JSONObject();
    Long longTeamId = Long.valueOf(1);
    mockTeam.put("id", longTeamId);
    testTeams.add(mockTeam); 
    Long id = Long.valueOf(1);
    Long seasonId = Long.valueOf(2);
    Long scoringPeriod = Long.valueOf(3);
    testObjFull.put("id", id);
    testObjFull.put("seasonId", seasonId);
    testObjFull.put("scoringPeriodId", scoringPeriod);
    testObjFull.put("schedule", (Object) testSchedule);
    testObjFull.put("teams", (JSONArray) testTeams);
  }

  @Test
  public void testLeagueConstructor() {
    league = new League(testObjFull, 1, 1);
    assertTrue(league instanceof League);

    assertNotNull(League.queryWeek);
    assertNotNull(League.querySeason);

    assertNotNull(league.leagueId);
    assertNotNull(league.seasonId);
    assertNotNull(league.scoringPeriodId);
    assertNotNull(league.teamsJson);

    assertEquals(1, league.leagueId);
    assertEquals(2, league.seasonId);
    assertEquals(3, league.scoringPeriodId);

    assertTrue(league.scheduleJson instanceof JSONArray);
    assertTrue(league.teamsJson instanceof JSONArray);
  }

  @Test
  public void generateTeamsTest() throws Exception {
    league = new League(testObjFull, 1, 1);
    league.generateTeams();
    assertFalse(league.teams.isEmpty());
    assertTrue(league.teams instanceof ArrayList);
  }
  
  @Test
  public void getTeamsTest() {
    league = new League(testObjFull, 1, 1);
    league.generateTeams();
    List<Team> listOfTeams = league.getTeams();
    assertTrue(listOfTeams instanceof List<?>);
    assertEquals(1, listOfTeams.size());
  }

  // @Test
  // public void testLeagueConstructed() {
  //   League testLeague = new League(testObjFull);
  //   assertTrue(testLeague instanceof League);
  // }

  // @Test
  // public void testValidateLeagueInfoException() {
  //   testObjMissingAttribute = new JSONObject();
  //   League testLeague = new League(testObjMissingAttribute);
  //   assertThrows(
  //     NullPointerException.class,
  //     () -> {
  //       testLeague.validateLeagueInfo();
  //     }
  //   );
  // }
  // @Test
  // public void testLeague() {
  //   assertTrue(testObj instanceof JSONObject);
  // }
  // @Test
  // public void testLeagueHasTeams() {
  //   League testLeague = new League(testObj);
  //   assertFalse(testLeague.getTeams().isEmpty());
  // }
}
