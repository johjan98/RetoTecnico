package dao;

import database.DataBaseConnection;
import model.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
