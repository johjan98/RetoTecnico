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
      try{
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
        System.out.println("Query error: " + e);
      }
    }catch (SQLException e){
      System.out.println("Error: " + e);
    }

    return idQuestion;
  }
}
