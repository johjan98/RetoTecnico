package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

  private DataBaseConnection(){}
  public static Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(
              "jdbc:postgresql://localhost:5432/preguntas_respuestas",
              "postgres",
              "J0hj4nGuit4r2012");

      System.out.println("DB connection successful");

    } catch (SQLException e) {
      System.out.println("Error de conexión: " + e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    return connection;
  }
}
