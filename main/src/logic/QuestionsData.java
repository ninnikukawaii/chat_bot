package logic;

import logic.exception.FileReadException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionsData implements logic.interfaces.QuestionsData {
    private ArrayList<Question> mQuestions;
    private Random random;

    public QuestionsData(String filename) throws FileReadException {
        mQuestions = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineParts = line.split("~");
                if (lineParts.length != 2){
                    throw new IllegalStateException("Incorrect number of arguments");
                }
                mQuestions.add(new Question(lineParts[0], lineParts[1]));
            }
        }
        catch (IOException | IllegalStateException ex) {
            throw new FileReadException(ex.getMessage());
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

    public List<Question> getAllQuestions() {
        return new ArrayList<>(mQuestions);
    }

    public int getSize() {
        return mQuestions.size();
    }
}
