package model;

public class Answer {
  private String statement;
  private int idQuestion;
  private boolean correct;

  public Answer(String statement, int idQuestion, boolean correct) {
    this.statement = statement;
    this.idQuestion = idQuestion;
    this.correct = correct;
  }

  public String getStatement() {
    return statement;
  }

  public void setStatement(String statement) {
    this.statement = statement;
  }

  public int getIdQuestion() {
    return idQuestion;
  }

  public void setIdQuestion(int idQuestion) {
    this.idQuestion = idQuestion;
  }

  public boolean isCorrect() {
    return correct;
  }

  public void setCorrect(boolean correct) {
    this.correct = correct;
  }
}
