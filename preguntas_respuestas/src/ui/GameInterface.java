package ui;

import dao.AnswerDAO;
import dao.PlayerDAO;
import dao.QuestionDAO;
import model.Player;
import model.Question;

import javax.swing.*;

public class GameInterface {
  private static final int FIRST_PRIZE = 100;
  private static final int SECOND_PRIZE = 400;
  private static final int THIRD_PRIZE = 1000;
  private static final int FOURTH_PRIZE = 1500;
  private static final int FIFTH_PRIZE = 3000;
  private static final String TITLE = "Preguntas y Respuestas";
  private GameInterface() {}

  public static void showRoundInfo(Player player){
    if(player.getCurrentRound() >= 6){
      gameWon(player);
    }else {
      String[] options = {"Continuar", "Retirarse"};

      int optionSelected = JOptionPane.showOptionDialog(
              null,
              "Jugador: " + player.getName()
                      + "\nPremio acumulado: $ " + player.getAcumulatedPrize()
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
        gameWon(player);
      }
    }
  }

  private static void showQuestion(Player player){

    Question question = QuestionDAO.selectQuestion(player.getCurrentRound());
    String[] answers = AnswerDAO.selectAnswers(question);
    String completeQuestion = question.formatQuestion(answers);

    String[] options = {"Opción 1", "Opción 2", "Opción 3", "Opción 4"};

    int optionSelected = JOptionPane.showOptionDialog(
            null,
            completeQuestion,
            TITLE,
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
    );

    boolean isCorrect = AnswerDAO.verifyAnswer(answers[optionSelected], answers[4]);

    if(isCorrect){
      JOptionPane.showMessageDialog(null, "Correcto");
      switch (player.getCurrentRound()){
        case 1 -> player.setAcumulatedPrize(player.getAcumulatedPrize() + FIRST_PRIZE);
        case 2 -> player.setAcumulatedPrize(player.getAcumulatedPrize() + SECOND_PRIZE);
        case 3 -> player.setAcumulatedPrize(player.getAcumulatedPrize() + THIRD_PRIZE);
        case 4 -> player.setAcumulatedPrize(player.getAcumulatedPrize() + FOURTH_PRIZE);
        case 5 -> player.setAcumulatedPrize(player.getAcumulatedPrize() + FIFTH_PRIZE);
        default -> {}
      }
      player.setCurrentRound(player.getCurrentRound() + 1);
      showRoundInfo(player);
    }else {
      gameLost(player);
    }
  }

  private static void gameWon(Player player){
    String message;
    String[] options = {"Jugar de nuevo", "Salir"};
    if(player.getCurrentRound() >= 6) {
      player.setTotalGames(player.getTotalGames() + 1);
      player.setGamesWon(player.getGamesWon() + 1);
      player.setTotalPrize(player.getTotalPrize() + player.getAcumulatedPrize());
      message = "Felicidades " + player.getName()
              + ".\nGanaste el juego con un total de $ " + player.getAcumulatedPrize()
              + ".\nAcumulas $ " + player.getTotalPrize() + " por todas tus "+ player.getTotalGames()
              + " partidas jugadas. \nVuelve pronto.";
      PlayerDAO.saveDataPlayer(player);
    }else {
      player.setTotalGames(player.getTotalGames() + 1);
      player.setTotalPrize(player.getTotalPrize() + player.getAcumulatedPrize());
      message = "Felicidades " + player.getName()
              + ".\nTe retiras con un total de $ " + player.getAcumulatedPrize()
              + ".\nAcumulas $ " + player.getTotalPrize() + " en tus "+ player.getTotalGames()
              + " partidas jugadas. \nVuelve pronto.";
      PlayerDAO.saveDataPlayer(player);
    }

    int optionSelected = JOptionPane.showOptionDialog(
            null,
            message,
            TITLE,
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[1]);

    player.setAcumulatedPrize(0);
    player.setCurrentRound(1);

    if (optionSelected == 0){
      showRoundInfo(player);
    }else {
      MainMenuInterface.mainMenu();
    }
  }

  private static void gameLost(Player player){

    player.setTotalGames(player.getTotalGames() + 1);
    String[] options = {"Jugar de nuevo", "Salir"};
    String message = "Respuesta incorrecta."
            + "\nPremio: $ 0 "
            + "\nTotal acumulado en " + player.getTotalGames() + " partidas: $ " + player.getTotalPrize()
            + "\nVuelve a intentarlo.";

    int optionSelected = JOptionPane.showOptionDialog(
            null,
            message,
            TITLE,
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[1]);

    player.setAcumulatedPrize(0);
    player.setCurrentRound(1);

    if (optionSelected == 0){
      showRoundInfo(player);
    }else {
      MainMenuInterface.mainMenu();
    }
  }
}
