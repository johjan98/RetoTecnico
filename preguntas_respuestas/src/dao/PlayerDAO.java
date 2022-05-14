package dao;

import database.DataBaseConnection;
import model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PlayerDAO {
  public static Player verifyPlayer(String name){
    ResultSet rs = null;
    Player player = null;
    try(Connection cnxn = DataBaseConnection.getConnection()){
      PreparedStatement ps = null;
      try {
        String query = "SELECT id FROM player WHERE name=?";
        ps = cnxn.prepareStatement(query);
        ps.setString(1,name);
        rs = ps.executeQuery();
        int idPlayer = -1;
        while (rs.next()){
          idPlayer = rs.getInt("id");
        }

        if (idPlayer != -1){ //Si el jugador existe en la bd
          player = new Player(name, idPlayer);
        }else {
          player = createPlayer(name);
        }
      }catch (SQLException e){
        System.out.println("Error query: " + e);
      }
    }catch (SQLException e){
      System.out.println("Error: " + e);
    }
    return player;
  }

  private static Player createPlayer(String name){
    Player player = null;
    try(Connection cnxn = DataBaseConnection.getConnection()){
      PreparedStatement ps = null;
      ResultSet rs = null;
      try{
        String query = "INSERT INTO player(name, games_won, total_games, total_prize) VALUES (?, 0, 0, 0)";
        ps = cnxn.prepareStatement(query);
        ps.setString(1, name);
        ps.executeUpdate();
        System.out.println("Jugador creado con éxito.");

        String idQuery = "SELECT id FROM player WHERE name=?";
        ps = cnxn.prepareStatement(idQuery);
        ps.setString(1, name);
        rs = ps.executeQuery();
        int idPlayer = -1;
        while (rs.next()){
          idPlayer = rs.getInt("id");
        }
        if (idPlayer != -1) {
          player = new Player(name, idPlayer);
        }
      }catch (SQLException e){
        System.out.println("Error query: " + e);
      }
    }catch (SQLException e){
      System.out.println("Error: " + e);
    }
    return player;
  }
}
