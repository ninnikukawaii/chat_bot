package logic.tests;

import logic.Question;
import logic.interfaces.QuestionsData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestData implements QuestionsData {

    private ArrayList<Question> testQuestions;
    private Random random;

    public TestData(){
        testQuestions = new ArrayList<>();
        testQuestions.add(new Question("Как называется пятнистая лошадь?", "Пинто"));
        testQuestions.add(new Question("В какой стране нет комаров?", "Исландия"));

        random = new Random();
    }

    @Override
    public Question getQuestion() {
        int index = random.nextInt(testQuestions.size());
        return testQuestions.get(index);
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
