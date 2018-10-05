package logic.interfaces;

import logic.Question;

import java.util.ArrayList;

public interface QuestionsData {
    Question getQuestion();
    ArrayList<Question> getAllQuestions();
    int getSize();
}
