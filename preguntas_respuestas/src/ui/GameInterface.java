package ui;

import dao.AnswerDAO;
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
      if(player.getCurrentRound() == 6){
        MainMenuInterface.mainMenu();
      }else {
        showRoundInfo(player);
      }
    }
  }

  private static void gameWon(Player player){

  }
}
