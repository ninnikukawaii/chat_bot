package logic.tests;

import logic.Question;
import logic.interfaces.QuestionsData;

import java.util.ArrayList;
import java.util.List;

public class TestData implements QuestionsData {

    private ArrayList<Question> testQuestions;

    public TestData(){
        testQuestions = new ArrayList<>();
        testQuestions.add(new Question("Как называется пятнистая лошадь?", "Пинто"));
        testQuestions.add(new Question("В какой стране нет комаров?", "Исландия"));
    }

    @Override
    public Question getQuestion() {
        return null;
    }

    @Override
    public List<Question> getAllQuestions() {
        return new ArrayList<>(testQuestions);
    }

    @Override
    public int getSize() {
        return testQuestions.size();
    }
}
