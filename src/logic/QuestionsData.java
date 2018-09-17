package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QuestionsData {
    private ArrayList<Question> mQuestions;
    private Random random;

    public QuestionsData(String filename) {
        mQuestions = new ArrayList<Question>();
        mQuestions.add(new Question("lol?", "kek"));
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineParts = line.split("|");
                mQuestions.add(new Question(lineParts[0], lineParts[1]));
            }
        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }
        random = new Random();
    }

    public Question getQuestion() {
        int index = random.nextInt(mQuestions.size());
        return mQuestions.get(index);
    }
}
