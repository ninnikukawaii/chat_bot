package logic.tests;

import logic.Question;
import logic.QuestionsData;
import logic.User;
import logic.enums.Command;
import logic.enums.UserState;
import logic.exception.FileReadException;
import logic.handlers.PhrasesHandler;
import logic.handlers.RequestHandler;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RequestHandlerTest {
    private String path = "testQuestions.txt";
    private User user = new User(0L);
    private QuestionsData questionsData;
    private RequestHandler requestHandler;

    public void trySetValue() throws FileReadException {
        questionsData = new QuestionsData(path);
        requestHandler = new RequestHandler(questionsData);
    }

    @Test
    public void testExitDialog() throws FileReadException {
        trySetValue();
        ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.EXIT, "none", user);
        assertThat(result, hasItem(PhrasesHandler.getEndPhrase()));
        assertEquals(UserState.EXIT, user.getState());
    }

    @Test
    public void testExitQuiz() throws FileReadException {
        trySetValue();
        user.setState(UserState.QUIZ);
        ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.EXIT, "none", user);
        assertThat(result, hasItem(PhrasesHandler.getEndQuizPhrase()));
        assertEquals(UserState.DIALOG, user.getState());
    }

    @Test
    public void testDialogHelp() throws FileReadException {
        trySetValue();
        ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.HELP, "none", user);
        assertThat(result, hasItem(PhrasesHandler.getHelp()));
    }

    @Test
    public void testQuiz() throws FileReadException {
        trySetValue();
        ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.QUIZ, "none", user);
        assertThat(result, hasItem(PhrasesHandler.getStartQuizPhrase()));
        assertEquals(UserState.QUIZ, user.getState());
    }

    @Test
    public void testQuizHelp() throws FileReadException {
        trySetValue();
        user.setState(UserState.QUIZ);
        ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.HELP, "none", user);
        assertThat(result, hasItem(PhrasesHandler.getQuizHelp()));
    }

    @Test
    public void testGiveUp() throws FileReadException{
        trySetValue();
        user.setState(UserState.QUIZ);
        user.setLastQuestion(new Question("Как называется пятнистая лошадь?", "пинто"));
        ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.GIVE_UP, "none", user);
        assertThat(result, hasItem("Правильный ответ был: пинто"));
    }

    @Test
    public void testRepeatQuestion() throws FileReadException {
        trySetValue();
        user.setState(UserState.QUIZ);
        user.setLastQuestion(new Question("Как называется пятнистая лошадь?", "пинто"));
        ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.REPEAT_QUESTION, "none", user);
        assertThat(result, hasItem(user.getLastQuestion().getQuestion()));
    }

    @Test
    public void testUserAnswers() throws FileReadException {
        trySetValue();
        user.setState(UserState.QUIZ);
        user.setLastQuestion(new Question("Как называется пятнистая лошадь?", "пинто"));
        ArrayList<String> resultRightAnswer = requestHandler.getAnswerByCommandAndRequest(Command.NONE, "пинто", user);
        ArrayList<String> resultWrongAnswer = requestHandler.getAnswerByCommandAndRequest(Command.NONE, "вороная", user);
        assertThat(resultRightAnswer, hasItem(PhrasesHandler.getCorrectAnswerPhrase()));
        assertThat(resultWrongAnswer, hasItem(PhrasesHandler.getIncorrectAnswerPhrase()));
    }

    @Test
    public void testIncorrectPhrase() throws FileReadException {
        trySetValue();
        ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.NONE, "none", user);
        assertThat(result, hasItem(PhrasesHandler.getUnknownPhrase()));
    }
}
