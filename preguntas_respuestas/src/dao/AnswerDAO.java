package dao;

import database.DataBaseConnection;
import model.Answer;
import model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnswerDAO {
  private static final Logger logger = Logger.getLogger(AnswerDAO.class.getName());
  private static final String ERROR_QUERY = "Error query: ";
  private AnswerDAO(){}
  public static void addAnswer(Answer answer){
    Connection cnxn = DataBaseConnection.getConnection();
    String query = "INSERT INTO answer(id_question, statement, correct) VALUES (?,?,?)";
    try(PreparedStatement ps = cnxn.prepareStatement(query)){
      ps.setInt(1, answer.getIdQuestion());
      ps.setString(2, answer.getStatement());
      ps.setBoolean(3, answer.isCorrect());
      ps.executeUpdate();
      logger.info("Respuesta agregada con éxito.");
    }catch (SQLException e){
      logger.log(Level.SEVERE,ERROR_QUERY , e);
    }
  }

  public static void deleteAnswers(int idQuestion) {
    Connection cnxn = DataBaseConnection.getConnection();
    String query = "DELETE FROM answer WHERE id_question = ?";
    try (PreparedStatement ps = cnxn.prepareStatement(query)) {
      ps.setInt(1, idQuestion);
      ps.executeUpdate();
      logger.info("Respuestas eliminadas.");
    } catch (SQLException e) {
      logger.log(Level.SEVERE, ERROR_QUERY, e);
    }
  }

  public static String[] selectAnswers(Question question){
    Connection cnxn = DataBaseConnection.getConnection();
    String query = "SELECT id, statement FROM answer WHERE id_question = ? ORDER BY rand()";
    ResultSet rs = null;
    String[] answers = {"", "", "", "", ""};
    try(PreparedStatement ps = cnxn.prepareStatement(query)){
      ps.setInt(1, question.getId());
      rs = ps.executeQuery();
      int index = 0;
      while (rs.next()){
        answers[index] = rs.getString("statement");
        index++;
      }
      String correctAnswer = "SELECT id FROM answer WHERE id_question = ? AND correct = true";
      try(PreparedStatement ps1 = cnxn.prepareStatement(correctAnswer)) {
        ps1.setInt(1, question.getId());
        rs = ps1.executeQuery();

        while (rs.next()) {
          answers[4] = rs.getString("id");
        }
      }
    }catch (SQLException e){
      logger.log(Level.SEVERE,ERROR_QUERY , e);
    }
    return answers;
  }

  public static boolean verifyAnswer(String answer, String idCorrectAnswer){
    Connection cnxn = DataBaseConnection.getConnection();
    String query = "SELECT statement FROM answer WHERE id = ?";
    ResultSet rs = null;
    boolean answerValidated = false;
    try(PreparedStatement ps = cnxn.prepareStatement(query)){

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
      logger.log(Level.SEVERE,ERROR_QUERY , e);
    }

    return answerValidated;
  }
}
