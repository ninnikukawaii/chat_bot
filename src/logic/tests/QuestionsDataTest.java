package logic.tests;

import logic.Question;
import logic.QuestionsData;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class QuestionsDataTest {
    private String path = "testQuestions.txt";

    @Test
    public void testQuestionBase() {
        try
        {
            QuestionsData questionsData = new QuestionsData(path);
            Question question = questionsData.getQuestion();
            assertThat(questionsData.getAllQuestions(), hasItem(question));
            //assertTrue(questionsData.getAllQuestions().contains(new Question("Как называется пятнистая лошадь?", "пинто")));
        }
        catch (IOException e){
            System.out.println("Wrong testQuestionBase: " + e.getMessage());
            fail();
        }
    }

    @Test(expected = IOException.class)
    public void testWrongPath() throws IOException{
        QuestionsData wrongData = new QuestionsData("beliberda");
    }

    @Test
    public void testGetQuestion() {
        try
        {
            QuestionsData questionsData = new QuestionsData(path);
            assertNotEquals("", questionsData.getQuestion());
        }
        catch (IOException e){
            System.out.println("Wrong testGetQuestion: " + e.getMessage());
            fail();
        }
    }
}