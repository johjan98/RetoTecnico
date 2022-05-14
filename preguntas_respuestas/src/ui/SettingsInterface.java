package ui;

import dao.AnswerDAO;
import dao.QuestionDAO;
import model.Answer;
import model.Question;

import javax.swing.*;

public class SettingsInterface {
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
      default -> {}
    }

  }

  private static void addQuestion(){
    String[] levelOptions = {"1", "2", "3", "4", "5"};

    String question = JOptionPane.showInputDialog(
            null,
            "Ingrese una pregunta.",
            JOptionPane.QUESTION_MESSAGE);

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
      System.out.println("id btenido:" + idQuestion);
      addAnswers(idQuestion);
    }else {
      System.out.println("no se obtuvo id");
      settingsMenu();
    }
  }

  private static void addAnswers(int idQuestion){
    final String CORRECT_ANSWER = "Respuesta correcta";
    final String WRONG_ANSWER = "Respuesta incorrecta";

    String correctAnswer = JOptionPane.showInputDialog(
            null,
            CORRECT_ANSWER);
    AnswerDAO.addAnswer(createAnswer(correctAnswer, idQuestion, true));

    String wrongAnswer = JOptionPane.showInputDialog(
            null,
            WRONG_ANSWER);
    AnswerDAO.addAnswer(createAnswer(wrongAnswer, idQuestion, false));

    wrongAnswer = JOptionPane.showInputDialog(
            null,
            WRONG_ANSWER);
    AnswerDAO.addAnswer(createAnswer(wrongAnswer, idQuestion, false));

    wrongAnswer = JOptionPane.showInputDialog(
            null,
            WRONG_ANSWER);
    AnswerDAO.addAnswer(createAnswer(wrongAnswer, idQuestion, false));

    settingsMenu();

  }

  private static Question createQuestion(String statement, int level){
    return new Question(statement, level);
  }

  private static Answer createAnswer(String answer, int idQuestion, boolean correct){
    return new Answer(answer, idQuestion, correct);
  }
}
