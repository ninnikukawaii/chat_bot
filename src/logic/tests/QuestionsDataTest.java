package logic.tests;

import logic.Question;
import logic.QuestionsData;
import logic.exception.FileReadException;
import org.junit.Test;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class QuestionsDataTest {
    private String pathToCorrectQuestions = "resources/testQuestions.txt";

    @Test
    public void testQuestionBase() throws FileReadException {
        QuestionsData questionsData = new QuestionsData(pathToCorrectQuestions);
            assertThat(questionsData.getAllQuestions(), hasItem(new Question("Как называется пятнистая лошадь?", "Пинто")));
    }

    @Test(expected = FileReadException.class)
    public void testWrongPath() throws FileReadException {
        new QuestionsData("beliberda");
    }

    @Test
    public void testGetQuestion() throws FileReadException {
        QuestionsData questionsData = new QuestionsData(pathToCorrectQuestions);
        assertThat(questionsData.getQuestion(), is(not(equalTo(""))));
    }

    @Test(expected = FileReadException.class)
    public void testIncorrectDataInFile() throws FileReadException {
        String pathToIncorrectQuestions = "testIncorrectQuestions.txt";
        new QuestionsData(pathToIncorrectQuestions);
    }
}