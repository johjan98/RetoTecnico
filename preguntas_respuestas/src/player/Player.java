package player;

public class Player {
  private final String name;
  private static int id = 0;
  private int currentRound;
  private double acumulatedPrize;

  public Player(String name) {
    this.name = name;
    id++;
  }

  public void startGame(){

  }

  public void finishGame(){

  }

  public void nextQuestion(){

  }

  public String getName() {
    return name;
  }
}
