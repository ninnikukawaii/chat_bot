package logic.tests;

import logic.Question;
import logic.RequestProcessor;
import logic.interfaces.QuestionsData;
import logic.User;
import logic.enums.Command;
import logic.enums.UserState;
import logic.exception.FileReadException;
import logic.handlers.PhrasesHandler;
import logic.handlers.RequestHandler;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RequestHandlerTest {
    private User user = new User(0L);
    private QuestionsData questionsData;
    private RequestHandler requestHandler;

    @Before
    public void setUp() {
        questionsData = new TestData();
        requestHandler = new RequestHandler(questionsData);
    }

    @Test
    public void testExitDialog() {
        user.setState(UserState.DIALOG);
        List<String> result = requestHandler.getAnswerByProcessor(Command.EXIT, user);
        assertThat(result, hasItem(PhrasesHandler.getEndPhrase()));
        assertEquals(UserState.EXIT, user.getState());
    }

    @Test
    public void testExitQuiz() {
        user.setState(UserState.QUIZ);
        List<String> result = requestHandler.getAnswerByProcessor(Command.EXIT, user);
        assertThat(result, hasItem(PhrasesHandler.getEndQuizPhrase()));
        assertEquals(UserState.DIALOG, user.getState());
    }

    @Test
    public void testDialogHelp() {
        user.setState(UserState.DIALOG);
        List<String> result = requestHandler.getAnswerByProcessor(Command.HELP, user);
        assertThat(result, hasItem(PhrasesHandler.getDialogHelp()));
    }

    @Test
    public void testQuiz() {
        user.setState(UserState.DIALOG);
        List<String> result = requestHandler.getAnswerByProcessor(Command.QUIZ, user);
        assertThat(result, hasItem(PhrasesHandler.getStartQuizPhrase()));
        assertEquals(UserState.QUIZ, user.getState());
    }

    @Test
    public void testQuizHelp() {
        user.setState(UserState.QUIZ);
        List<String> result = requestHandler.getAnswerByProcessor(Command.HELP, user);
        assertThat(result, hasItem(PhrasesHandler.getQuizHelp()));
    }

    @Test
    public void testGiveUp() {
        user.setState(UserState.QUIZ);
        user.setLastQuestion(new Question("Как называется пятнистая лошадь?", "пинто"));
        List<String> result = requestHandler.getAnswerByProcessor(Command.GIVE_UP, user);
        assertThat(result, hasItem("Правильный ответ был: пинто"));
    }

    @Test
    public void testRepeatQuestion() {
        user.setState(UserState.QUIZ);
        user.setLastQuestion(new Question("Как называется пятнистая лошадь?", "пинто"));
        List<String> result = requestHandler.getAnswerByProcessor(Command.REPEAT_QUESTION, user);
        assertThat(result, hasItem(user.getLastQuestion().getQuestion()));
    }

    @Test
    public void testEndDialog() {
        testEndDialogWithInitialState(UserState.START);
        testEndDialogWithInitialState(UserState.DIALOG);
        testEndDialogWithInitialState(UserState.QUIZ);
    }

    private void testEndDialogWithInitialState(UserState state) {
        user.setState(state);
        requestHandler.getAnswerByProcessor(Command.END_DIALOG, user);
        assertEquals(UserState.EXIT, user.getState());
    }

    @Test
    public void testUserAnswers() {
        user.setState(UserState.QUIZ);
        user.setLastQuestion(new Question("Как называется пятнистая лошадь?", "пинто"));
        List<String> resultRightAnswer = requestHandler.getAnswerByProcessor(new RequestProcessor("пинто"), user);
        List<String> resultWrongAnswer = requestHandler.getAnswerByProcessor(new RequestProcessor("неверный ответ"), user);
        assertThat(resultRightAnswer, hasItem(PhrasesHandler.getCorrectAnswerPhrase()));
        assertThat(resultWrongAnswer, hasItem(PhrasesHandler.getIncorrectAnswerPhrase()));
    }

    @Test
    public void testIncorrectPhrase() {
        List<String> result = requestHandler.getAnswerByProcessor(new RequestProcessor("qwerty"), user);
        assertThat(result, hasItem(PhrasesHandler.getUnknownPhrase()));
    }
}
