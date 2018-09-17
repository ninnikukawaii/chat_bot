package logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionsDataTest {

    @Test
    public void getQuestion() {
        String path = System.getProperty("user.dir") + "\\" + "questions.txt";
        QuestionsData questionsData = new QuestionsData(path);

        
    }
}