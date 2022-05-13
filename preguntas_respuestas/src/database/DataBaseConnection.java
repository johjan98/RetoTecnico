package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
  public static Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(
              "jdbc:postgresql://localhost:5432/preguntas_respuestas",
              "postgres",
              "");
      if (connection != null) {
        System.out.println("DB connection successful");
      }
    } catch (SQLException e) {
      System.out.println("Error de conexión: " + e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    return connection;
  }
}
