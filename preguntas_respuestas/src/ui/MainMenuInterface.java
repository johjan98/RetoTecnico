package ui;

import dao.AnswerDAO;
import dao.QuestionDAO;
import model.Answer;
import model.Player;
import model.Question;

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
    String[] options = {"Iniciar juego", "Retirarse"};

      String name = JOptionPane.showInputDialog(
              null,
              "Ingrese su nombre.",
              JOptionPane.QUESTION_MESSAGE);
      System.out.println(name);
      Player player = new Player(name);

      int optionSelected = JOptionPane.showOptionDialog(
              null,
              "Jugador: "+player.getName(),
              TITLE,
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.QUESTION_MESSAGE,
              null,
              options,
              options[0]);

      switch (optionSelected){
        case 0 -> GameInterface.startGame();
        case 1 -> player.finishGame();
      }


  }
}
