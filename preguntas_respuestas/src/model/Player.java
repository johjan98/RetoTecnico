package model;

public class Player {
  private final String name;
  private int id;
  private int currentRound = 0;
  private double acumulatedPrize = 0;

  public Player(String name, int id) {
    this.name = name;
    this.id = id;
  }
  public String getName() {
    return name;
  }
}
