package logic.tests;

import logic.Question;
import logic.QuestionsData;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class QuestionsDataTest {
    private String path = "testquestions.txt";

    @Test
    public void testQuestionBase() {
        try
        {
            QuestionsData questionsData = new QuestionsData(path);
            assertThat(questionsData.getAllQuestions(), hasItem(new Question("Как называется пятнистая лошадь?", "пинто")));
            //assertTrue(questionsData.getAllQuestions().contains());
        }
        catch (IOException e){
            System.out.println("Wrong testQuestionBase: " + e.getMessage());
            assertTrue(false);
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
            assertTrue(false);
        }
    }
}