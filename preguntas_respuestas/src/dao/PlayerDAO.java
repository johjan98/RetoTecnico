package dao;

import database.DataBaseConnection;
import model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO {
  private PlayerDAO(){}
  public static Player verifyPlayer(String name){
    ResultSet rs = null;
    Player player = null;
    try(Connection cnxn = DataBaseConnection.getConnection()){
      PreparedStatement ps = null;

      String query = "SELECT id FROM player WHERE name=?";
      ps = cnxn.prepareStatement(query);
      ps.setString(1,name);
      rs = ps.executeQuery();
      int idPlayer = -1;
      while (rs.next()){
        idPlayer = rs.getInt("id");
      }

      if (idPlayer != -1){ //Si el jugador existe en la bd
        player = getPlayerData(idPlayer);
      }else {
        player = createPlayer(name);
      }
    }catch (SQLException e){
      System.out.println("Error query: " + e);
    }
    return player;
  }

  private static Player getPlayerData(int id){
    Player player = null;
    ResultSet rs = null;
    try(Connection cnxn = DataBaseConnection.getConnection()){
      PreparedStatement ps = null;
      String query = "SELECT * FROM player WHERE id = ?";
      ps = cnxn.prepareStatement(query);
      ps.setInt(1, id);
      rs = ps.executeQuery();

      while (rs.next()){
        player = new Player(
                rs.getString("name"),
                rs.getInt("id"),
                rs.getInt("games_won"),
                rs.getInt("total_prize"),
                rs.getInt("total_games"));
      }
    }catch (SQLException e){
      System.out.println("Error query: " + e);
    }
    return player;
  }

  private static Player createPlayer(String name){
    Player player = null;
    try(Connection cnxn = DataBaseConnection.getConnection()){
      PreparedStatement ps = null;
      ResultSet rs = null;

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
    return player;
  }

  public static void saveDataPlayer(Player player){
    try(Connection cnxn =  DataBaseConnection.getConnection()){
      PreparedStatement ps = null;
      String query = "UPDATE player SET games_won = ?, total_games = ?, total_prize = ? WHERE id = ?";
      ps = cnxn.prepareStatement(query);
      ps.setInt(1, player.getGamesWon());
      ps.setInt(2, player.getTotalGames());
      ps.setInt(3, player.getTotalPrize());
      ps.setInt(4, player.getId());
      int rowsAffected = ps.executeUpdate();
      if (rowsAffected == 1){
        System.out.println("Jugador actualizado con éxito");
      }

    }catch (SQLException e){
      System.out.println("Error query: " + e);
    }
  }
}
