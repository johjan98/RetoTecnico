package ui;

import dao.AnswerDAO;
import dao.QuestionDAO;
import model.Answer;
import model.Question;

import javax.swing.*;
import java.util.logging.Logger;

public class SettingsInterface {
  private static final Logger logger = Logger.getLogger(SettingsInterface.class.getName());
  private SettingsInterface() {}

  private static final String TITLE = "Preguntas y Respuestas";
  public static void settingsMenu(){
    String[] options = {"Crear pregunta", "Volver"};


    int optionSelected = JOptionPane.showOptionDialog(
            null,
            "Menú de configuración.",
            TITLE,
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[1]);

    switch (optionSelected){
      case 0 -> addQuestion();
      case 1 -> MainMenuInterface.mainMenu();
      default -> logger.info("");
    }

  }

  private static void addQuestion(){
    String[] levelOptions = {"1", "2", "3", "4", "5"};

    String question = JOptionPane.showInputDialog(
            null,
            "Ingrese una pregunta.");
    if (question == null){
      settingsMenu();
    }

    int level = JOptionPane.showOptionDialog(
            null,
            "Nivel de dificultad ",
            TITLE,
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            levelOptions,
            levelOptions[0]);

    int idQuestion = QuestionDAO.createQuestion(createQuestion(question, level+1));
    if(idQuestion != -1){
      logger.info("id obtenido: " + idQuestion);
      addAnswers(idQuestion);
    }else {
      logger.info("no se obtuvo id");
      settingsMenu();
    }
  }

  private static void addAnswers(int idQuestion){
    final String CORRECT_ANSWER = "Respuesta correcta";
    final String WRONG_ANSWER = "Respuesta incorrecta";

    String correctAnswer = JOptionPane.showInputDialog(
            null,
            CORRECT_ANSWER);
    if(correctAnswer == null){
      QuestionDAO.deleteQuestion(idQuestion);
      AnswerDAO.deleteAnswers(idQuestion);
      settingsMenu();
    }else {
      AnswerDAO.addAnswer(createAnswer(correctAnswer, idQuestion, true));
    }

    String wrongAnswer = JOptionPane.showInputDialog(
            null,
            WRONG_ANSWER);
    if(wrongAnswer == null){
      QuestionDAO.deleteQuestion(idQuestion);
      AnswerDAO.deleteAnswers(idQuestion);
      settingsMenu();
    }else {
      AnswerDAO.addAnswer(createAnswer(wrongAnswer, idQuestion, false));
    }

    wrongAnswer = JOptionPane.showInputDialog(
            null,
            WRONG_ANSWER);
    if(wrongAnswer == null){
      QuestionDAO.deleteQuestion(idQuestion);
      AnswerDAO.deleteAnswers(idQuestion);
      settingsMenu();
    }else {
      AnswerDAO.addAnswer(createAnswer(wrongAnswer, idQuestion, false));
    }

    wrongAnswer = JOptionPane.showInputDialog(
            null,
            WRONG_ANSWER);
    if(wrongAnswer == null){
      QuestionDAO.deleteQuestion(idQuestion);
      AnswerDAO.deleteAnswers(idQuestion);
      settingsMenu();
    }else {
      AnswerDAO.addAnswer(createAnswer(wrongAnswer, idQuestion, false));
    }
    settingsMenu();

  }

  private static Question createQuestion(String statement, int level){
    return new Question(statement, level);
  }

  private static Answer createAnswer(String answer, int idQuestion, boolean correct){
    return new Answer(answer, idQuestion, correct);
  }
}
