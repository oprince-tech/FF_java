package com.oprincetech.ff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileManagerTest {

  @BeforeEach
  public void setUp() {
    System.setProperty("user.dir", "/mock/dir");
  }

  @AfterEach 
  public void tearDown() {
    System.clearProperty("user.dir");
  }

  @Test
  public void testFileManagerConstructorDataTypes() {
    FileManager fileManager = new FileManager(1, 2, 3);
    assertEquals(fileManager.workingDirectory, "/mock/dir");
    assertEquals(fileManager.leagueIdString, "1");
    assertEquals(fileManager.weekString, "2");
    assertEquals(fileManager.seasonString, "3");
    assertEquals(fileManager.dataDir, "/data/");
    assertEquals(fileManager.ext, ".json");
    assertTrue(fileManager.leagueIdString instanceof String);
    assertTrue(fileManager.weekString instanceof String);
    assertTrue(fileManager.seasonString instanceof String);
    assertTrue(fileManager.dataDir instanceof String);
    assertTrue(fileManager.ext instanceof String);
  }
  
  @Test
  public void testFileManagerGenerateFillenamePathDataTypes() {
    FileManager fileManager = new FileManager(1, 2, 3);
    fileManager.generateFilenamePath();
    assertEquals(fileManager.filename, "FF_3_wk2_1.json");
    assertEquals(fileManager.absPath, "/mock/dir/data/FF_3_wk2_1.json");
  }
}