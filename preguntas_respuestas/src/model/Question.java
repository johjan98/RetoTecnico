package model;

public class Question {
  private String statement;
  private int level;

  public Question(String statement, int level) {
    this.statement = statement;
    this.level = level;
  }

  public String getStatement() {
    return statement;
  }

  public void setStatement(String statement) {
    this.statement = statement;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }
}
