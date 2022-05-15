package ui;

import dao.PlayerDAO;
import model.Player;

import javax.swing.*;

public class MainMenuInterface {

  private MainMenuInterface(){}
  private static final String TITLE = "Preguntas y Respuestas";
  public static void mainMenu(){
    String[] options = {"Jugar", "Configuración", "Salir"};
    int optionSelected = JOptionPane.showOptionDialog(
              null,
              "Menú principal",
              TITLE,
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.WARNING_MESSAGE,
              null,
              options,
              options[0]);

    switch (optionSelected){
      case 0 -> playMenu();
      case 1 -> SettingsInterface.settingsMenu();
      default -> System.exit(0);
    }
  }

  private static void playMenu(){
    String[] options = {"Iniciar juego", "Salir"};
    String name = JOptionPane.showInputDialog(
              null,
              "Ingrese su nombre.");
    if(name == null){
      mainMenu();
    }
    Player player = PlayerDAO.verifyPlayer(name);

    int optionSelected = JOptionPane.showOptionDialog(
              null,
              "Jugador: "+player.getName(),
              TITLE,
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.QUESTION_MESSAGE,
              null,
              options,
              options[0]);

    if (optionSelected == 0) {
      GameInterface.showRoundInfo(player);
    } else if (optionSelected == 1) {
      MainMenuInterface.mainMenu();
    }
  }
}
