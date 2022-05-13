import database.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args){

    try(Connection cnxn = DataBaseConnection.getConnection();){
      PreparedStatement ps = null;
      try{
        String query = "INSERT INTO player(name, games_won, total_games, total_prize) VALUES ('Johjan', 2, 5, 100)";
        ps = cnxn.prepareStatement(query);
        ps.executeUpdate();
        System.out.println("Jugador creado con exito.");
      }catch (SQLException e){
        System.out.println("Query error: " + e);
      }
    }catch (SQLException e){
      System.out.println("Error: " + e);
    }
  }
}
