package logic;

import java.util.ArrayList;
import java.util.Random;

public class QuestionsData {
    private ArrayList<Question> mQuestions;
    private Random random;

    public QuestionsData(String filename) { //TODO: инициализация из файла
        mQuestions = new ArrayList<Question>();
        mQuestions.add(new Question("lol?", "kek"));

        random = new Random();
    }

    public Question getQuestion() {
        int index = random.nextInt(mQuestions.size());
        return mQuestions.get(index);
    }
}
