package logic.tests;

import logic.Question;
import logic.QuestionsData;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.Matchers.*;

public class QuestionsDataTest {
    private String path = "testQuestions.txt";

    @Test
    public void testQuestionBase() throws IOException {
        QuestionsData questionsData = new QuestionsData(path);
            assertThat(questionsData.getAllQuestions(), hasItem(new Question("Как называется пятнистая лошадь?", "Пинто")));
    }

    @Test(expected = IOException.class)
    public void testWrongPath() throws IOException {
        new QuestionsData("beliberda");
    }

    @Test
    public void testGetQuestion() throws IOException {
        QuestionsData questionsData = new QuestionsData(path);
        assertThat(questionsData.getQuestion(), is(not(equalTo(""))));
    }
}