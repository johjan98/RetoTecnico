package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DataBaseConnection {
  private static final String USER = "";
  private static final String PASSWORD = "";

  private DataBaseConnection(){}
  private static final Logger logger = Logger.getLogger(DataBaseConnection.class.getName());
  public static Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(
              "jdbc:mysql://j2agz9emt3a3.us-east-3.psdb.cloud/preguntas_resuestas_db?sslMode=VERIFY_IDENTITY",
              USER,
              PASSWORD);

      logger.info("DB connection successful");

    } catch (SQLException e) {
      logger.log(Level.SEVERE,"Error de conexión: " , e);
    }catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    return connection;
  }
}
