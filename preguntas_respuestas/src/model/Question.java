package model;

import java.util.List;

public class Question {
  private String statement;
  private int level;
  private int id;

  public Question(String statement, int level) {
    this.statement = statement;
    this.level = level;
  }
  public Question(int id, String statement, int level){
    this.id = id;
    this.statement = statement;
    this.level = level;
  }

  public String formatQuestion(String[] answers){
    return
            this.statement
                    + "\n1. " + answers[0]
                    + "\n2. " + answers[1]
                    + "\n3. " + answers[2]
                    + "\n4. " + answers[3]
    ;
  }

  public String getStatement() {
    return statement;
  }

  public int getLevel() {
    return level;
  }

  public int getId() {
    return id;
  }
}
