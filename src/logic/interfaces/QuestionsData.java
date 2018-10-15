package logic.interfaces;

import logic.Question;

import java.util.ArrayList;
import java.util.List;

public interface QuestionsData {
    Question getQuestion();
    List<Question> getAllQuestions();
    int getSize();
}
