package logic.interfaces;

import logic.Question;

import java.util.ArrayList;

public interface QuestionData {
    Question getQuestion();
    ArrayList<Question> getAllQuestions();
    int getSize();
}
