package dao;

import database.DataBaseConnection;
import model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDAO {
  private QuestionDAO() {}

  public static int createQuestion(Question question){
    ResultSet rs = null;
    int idQuestion = -1;
    try(Connection cnxn = DataBaseConnection.getConnection()){
      PreparedStatement ps = null;

      String queryCreate = "INSERT INTO question(statement, difficulty_level) VALUES (?, ?)";
      ps = cnxn.prepareStatement(queryCreate);
      ps.setString(1, question.getStatement());
      ps.setInt(2, question.getLevel());
      ps.executeUpdate();
      System.out.println("Pregunta creada con exito.");

      String idQuery = "SELECT id FROM question WHERE statement=?";
      ps = cnxn.prepareStatement(idQuery);
      ps.setString(1, question.getStatement());
      rs = ps.executeQuery();
      while (rs.next()){
        idQuestion = rs.getInt("id");
      }
    }catch (SQLException e){
      System.out.println("Error query: " + e);
    }

    return idQuestion;
  }

  public static void deleteQuestion(int id){
    try(Connection cnxn = DataBaseConnection.getConnection()){
      PreparedStatement ps = null;
      String query = "DELETE FROM question WHERE id = ?";
      ps = cnxn.prepareStatement(query);
      ps.setInt(1, id);
      ps.executeUpdate();
      System.out.println("Pregunta eliminada.");
    }catch (SQLException e){
      System.out.println("Error query: " + e);
    }
  }

  public static Question selectQuestion(int round){
    ResultSet rs = null;
    Question question = null;
    try(Connection cnxn = DataBaseConnection.getConnection()){
      PreparedStatement ps = null;
      String query = "SELECT id, statement FROM question WHERE difficulty_level = ? ORDER BY random() LIMIT 1";
      ps = cnxn.prepareStatement(query);
      ps.setInt(1, round);
      rs = ps.executeQuery();

      while (rs.next()){
        question = new Question(rs.getInt("id"), rs.getString("statement"), round);
      }
    }catch (SQLException e){
      System.out.println("Error query: " + e);
    }

    return question;
  }
}
