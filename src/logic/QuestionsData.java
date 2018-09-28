package logic;

import logic.interfaces.QuestionData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QuestionsData implements QuestionData {
    private ArrayList<Question> mQuestions;
    private Random random;

    public QuestionsData(String filename) throws IOException{
        mQuestions = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineParts = line.split("~");
                mQuestions.add(new Question(lineParts[0], lineParts[1].toLowerCase()));
            }
        }
        catch (IOException ex){
            throw ex;
        }
        random = new Random();
    }


    public Question getQuestion() {
        if (mQuestions.size() == 0) {
            return null;
        }
        int index = random.nextInt(mQuestions.size());
        return mQuestions.get(index);
    }

    public ArrayList<Question> getAllQuestions() {
        return new ArrayList<>(mQuestions);
    }

    public int getSize() {
        return mQuestions.size();
    }
}
