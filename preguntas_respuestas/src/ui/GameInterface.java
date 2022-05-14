package ui;

import model.Player;

import javax.swing.*;

public class GameInterface {
  private final int FIRST_PRIZE = 100;
  private final int SECOND_PRIZE = 400;
  private final int THIRD_PRIZE = 1000;
  private final int FOURTH_PRIZE = 1500;
  private final int FIFTH_PRIZE = 3000;
  private static final String TITLE = "Preguntas y Respuestas";
  private GameInterface() {}

  public static void showRoundInfo(Player player){
    String[] options = {"Continuar", "Retirarse"};

    int optionSelected = JOptionPane.showOptionDialog(
            null,
            "Jugador: " + player.getName()
                    + "\nPremio acumulado: " + player.getAcumulatedPrize()
                    + "\nRound: " + player.getCurrentRound(),
            TITLE,
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
    );

    if (optionSelected == 0) {
      showQuestion(player);
    } else if (optionSelected == 1) {
      MainMenuInterface.mainMenu();
    }
  }

  private static void showQuestion(Player player){

  }
}
