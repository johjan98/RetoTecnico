package ui;

import player.Player;

import javax.swing.*;

public class UserInterface {
  public static void mainMenu(){
    String[] options = {"Jugar", "Configuración", "Salir"};

    do {
      int optionSelected = JOptionPane.showOptionDialog(
              null,
              "Menú principal",
              "Preguntas y Respuestas",
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.WARNING_MESSAGE,
              null,
              options,
              options[0]);

      switch (optionSelected){
        case 0 ->{
          playMenu();
        }
        case 1 ->{
          settingsMenu();
        }
        default -> {
          System.exit(0);
        }
      }
    }while (true);
  }

  private static void playMenu(){
    String[] options = {"Iniciar juego", "Retirarse"};
    do {
      String name = JOptionPane.showInputDialog(
              null,
              "Ingrese su nombre.",
              JOptionPane.QUESTION_MESSAGE);
      System.out.println(name);
      Player player = new Player(name);

      int optionSelected = JOptionPane.showOptionDialog(
              null,
              "Jugador: "+player.getName(),
              "Preguntas y Respuestas",
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.QUESTION_MESSAGE,
              null,
              options,
              options[0]);

      switch (optionSelected){
        case 0 -> player.startGame();
        case 1 -> player.finishGame();
      }

    }while (true);
  }

  private static void settingsMenu(){

  }
}
