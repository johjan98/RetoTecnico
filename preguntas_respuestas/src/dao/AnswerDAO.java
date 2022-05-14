package dao;

import database.DataBaseConnection;
import model.Answer;
import model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnswerDAO {
  private AnswerDAO(){}
  public static void addAnswer(Answer answer){
    try(Connection cnxn = DataBaseConnection.getConnection()){
      PreparedStatement ps = null;

      String query = "INSERT INTO answer(id_question, statement, correct) VALUES (?,?,?)";
      ps = cnxn.prepareStatement(query);
      ps.setInt(1, answer.getIdQuestion());
      ps.setString(2, answer.getStatement());
      ps.setBoolean(3, answer.isCorrect());
      ps.executeUpdate();
      System.out.println("Respuesta agregada con éxito.");
    }catch (SQLException e){
      System.out.println("Error query: " + e);
    }
  }

  public static String[] selectAnswers(Question question){
    ResultSet rs = null;
    String[] answers = {"", "", "", "", ""};
    try(Connection cnxn = DataBaseConnection.getConnection()){
      PreparedStatement ps = null;
      String query = "SELECT id, statement FROM answer WHERE id_question = ? ORDER BY random()";
      ps = cnxn.prepareStatement(query);
      ps.setInt(1, question.getId());
      rs = ps.executeQuery();
      int index = 0;
      while (rs.next()){
        answers[index] = rs.getString("statement");
        index++;
      }

      String correctAnswer = "SELECT id FROM answer WHERE id_question = ? AND correct = true";
      ps = cnxn.prepareStatement(correctAnswer);
      ps.setInt(1, question.getId());
      rs = ps.executeQuery();

      while (rs.next()){
        answers[4] = rs.getString("id");
      }
    }catch (SQLException e){
      System.out.println("Error query: " + e);
    }
    return answers;
  }

  public static boolean verifyAnswer(String answer, String idCorrectAnswer){
    ResultSet rs = null;
    boolean answerValidated = false;
    try(Connection cnxn = DataBaseConnection.getConnection()){
      PreparedStatement ps = null;
      String query = "SELECT statement FROM answer WHERE id = ?";
      ps = cnxn.prepareStatement(query);
      ps.setInt(1, Integer.parseInt(idCorrectAnswer));
      rs = ps.executeQuery();

      String correctAnswer = "";
      while (rs.next()){
        correctAnswer = rs.getString("statement");
      }

      if(Objects.equals(answer, correctAnswer)){
        answerValidated = true;
      }

    }catch (SQLException e){
      System.out.println("Error query: " + e);
    }

    return answerValidated;
  }
}
