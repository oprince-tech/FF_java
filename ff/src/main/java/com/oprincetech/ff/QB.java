package com.oprincetech.ff;

class QB extends Position {

  Double stats3;
  Double stats24;
  Double total_yards;
  Double att;
  Double cmp;
  long completion_percentage;

  @Override
  public void generatePositionStats() {
    // System.out.println("generating qb stats");
    stats3 = (Double) stats_listings.get("3");
    // System.out.println("Stats3: " + stats3);
    stats24 = (Double) stats_listings.get("24");
    // System.out.println("Stats24: " + stats24);
    if (stats3 != null && stats24 != null) {
      total_yards = Double.sum(stats3, stats24);
    }
    // System.out.println("Total yards: " + total_yards);
    // att = (Double) stats_listings.get("0");
    // cmp = (Double) stats_listings.get("1");
    // completion_percentage = Math.round((cmp / att) * 100);

  }
}
