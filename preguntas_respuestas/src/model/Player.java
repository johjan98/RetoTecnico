package model;

public class Player {
  private final String name;
  private final int id;
  private int currentRound = 1;
  private int acumulatedPrize = 0;
  private int gamesWon = 0;
  private int totalPrize = 0;
  private int totalGames = 0;

  public Player(String name, int id) {
    this.name = name;
    this.id = id;
  }

  public Player(String name, int id, int gamesWon, int totalPrize, int totalGames) {
    this.name = name;
    this.id = id;
    this.gamesWon = gamesWon;
    this.totalPrize = totalPrize;
    this.totalGames = totalGames;
  }

  public String getName() {
    return name;
  }
  public int getAcumulatedPrize() {
    return acumulatedPrize;
  }
  public int getCurrentRound() {
    return currentRound;
  }

  public void setCurrentRound(int currentRound) {
    this.currentRound = currentRound;
  }

  public void setAcumulatedPrize(int acumulatedPrize) {
    this.acumulatedPrize = acumulatedPrize;
  }

  public int getGamesWon() {
    return gamesWon;
  }

  public void setGamesWon(int gamesWon) {
    this.gamesWon = gamesWon;
  }

  public int getTotalPrize() {
    return totalPrize;
  }

  public void setTotalPrize(int totalPrize) {
    this.totalPrize = totalPrize;
  }

  public int getTotalGames() {
    return totalGames;
  }

  public void setTotalGames(int totalGames) {
    this.totalGames = totalGames;
  }

  public int getId() {
    return id;
  }
}
