package logic;

import logic.Question;
import logic.interfaces.QuestionsData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TestData implements QuestionsData {

    private List<Question> testQuestions;
    private Iterator<Question> questionsIterator;

    public TestData(){
        testQuestions = new ArrayList<>();
        testQuestions.add(new Question("Как называется пятнистая лошадь?", "Пинто"));
        testQuestions.add(new Question("В какой стране нет комаров?", "Исландия"));

        questionsIterator = testQuestions.iterator();
    }

    @Override
    public Question getQuestion() {
        if (!questionsIterator.hasNext()) {
            questionsIterator = testQuestions.iterator();
        }
        return questionsIterator.next();
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
