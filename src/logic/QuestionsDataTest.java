package logic;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class QuestionsDataTest {
    private String path = System.getProperty("user.dir") + "\\" + "questions.txt";
    private QuestionsData questionsData = new QuestionsData(path);

    @Test
    public void testQuestionBase() {
        assertTrue(questionsData.getAllQuestions().contains(new Question("Как называется пятнистая лошадь?", "пинто")));
    }

    @Test
    public void testWrongPath() {
        QuestionsData wrongData = new QuestionsData("beliberda");
        assertEquals(0, wrongData.getSize());
    }

    @Test
    public void testGetQuestion() {
        assertNotEquals("", questionsData.getQuestion());
    }
}