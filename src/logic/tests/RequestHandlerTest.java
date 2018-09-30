package logic.tests;

import logic.Question;
import logic.QuestionsData;
import logic.User;
import logic.enums.Command;
import logic.enums.UserState;
import logic.handlers.PhrasesHandler;
import logic.handlers.RequestHandler;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RequestHandlerTest {
    private String path = "src\\logic\\tests\\testquestions.txt";

    @Test
    public void testExitDialog(){
        try{
            QuestionsData questionsData = new QuestionsData(path);
            RequestHandler requestHandler = new RequestHandler(questionsData);
            User user = new User();
            ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.EXIT, "none", user);
            assertThat(result, hasItem(PhrasesHandler.getEndPhrase()));
            assertEquals(UserState.EXIT, user.getState());
        }
        catch (IOException e){
            System.out.println("Wrong testExitDialog: " + e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void testExitQuiz(){
        try{
            QuestionsData questionsData = new QuestionsData(path);
            RequestHandler requestHandler = new RequestHandler(questionsData);
            User user = new User();
            user.setState(UserState.QUIZ);
            ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.EXIT, "none", user);
            assertThat(result, hasItem(PhrasesHandler.getEndQuizPhrase()));
            assertEquals(UserState.DIALOG, user.getState());
        }
        catch (IOException e){
            System.out.println("Wrong testExitQuiz: " + e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void testDialogHelp(){
        try{
            QuestionsData questionsData = new QuestionsData(path);
            RequestHandler requestHandler = new RequestHandler(questionsData);
            User user = new User();
            ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.HELP, "none", user);
            assertThat(result, hasItem(PhrasesHandler.getHelp()));
        }
        catch (IOException e){
            System.out.println("Wrong testDialogHelp: " + e.getMessage());
            assertTrue(false);
        }

    }

    @Test
    public void testQuiz(){
        try{
            QuestionsData questionsData = new QuestionsData(path);
            RequestHandler requestHandler = new RequestHandler(questionsData);
            User user = new User();
            ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.QUIZ, "none", user);
            assertThat(result, hasItem(PhrasesHandler.getStartQuizPhrase()));
            assertEquals(UserState.QUIZ, user.getState());
        }
        catch (IOException e){
            System.out.println("Wrong testQuiz: " + e.getMessage());
            assertTrue(false);
        }

    }

    @Test
    public void testQuizHelp(){
        try{
            QuestionsData questionsData = new QuestionsData(path);
            RequestHandler requestHandler = new RequestHandler(questionsData);
            User user = new User();
            user.setState(UserState.QUIZ);
            ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.HELP, "none", user);
            assertThat(result, hasItem(PhrasesHandler.getQuizHelp()));
        }
        catch (IOException e){
            System.out.println("Wrong testQuizHelp: " + e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void testGiveUp(){
        try{
            QuestionsData questionsData = new QuestionsData(path);
            RequestHandler requestHandler = new RequestHandler(questionsData);
            User user = new User();
            user.setState(UserState.QUIZ);
            user.setLastQuestion(new Question("Как называется пятнистая лошадь?", "пинто"));
            ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.GIVE_UP, "none", user);
            assertThat(result, hasItem("Правильный ответ был: пинто"));
        }
        catch (IOException e){
            System.out.println("Wrong testGiveUp: " + e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void testRepeatQuestion(){
        try{
            QuestionsData questionsData = new QuestionsData(path);
            RequestHandler requestHandler = new RequestHandler(questionsData);
            User user = new User();
            user.setState(UserState.QUIZ);
            user.setLastQuestion(new Question("Как называется пятнистая лошадь?", "пинто"));
            ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.REPEAT_QUESTION, "none", user);
            assertThat(result, hasItem(user.getLastQuestion().getQuestion()));
        }
        catch (IOException e){
            System.out.println("Wrong testGiveUp: " + e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void testUserAnswers(){
        try{
            QuestionsData questionsData = new QuestionsData(path);
            RequestHandler requestHandler = new RequestHandler(questionsData);
            User user = new User();
            user.setState(UserState.QUIZ);
            user.setLastQuestion(new Question("Как называется пятнистая лошадь?", "пинто"));
            ArrayList<String> resultRightAnswer = requestHandler.getAnswerByCommandAndRequest(Command.NONE, "пинто", user);
            ArrayList<String> resultWrongAnswer = requestHandler.getAnswerByCommandAndRequest(Command.NONE, "вороная", user);
            assertThat(resultRightAnswer, hasItem(PhrasesHandler.getCorrectAnswerPhrase()));
            assertThat(resultWrongAnswer, hasItem(PhrasesHandler.getIncorrectAnswerPhrase()));
        }
        catch (IOException e){
            System.out.println("Wrong testUserAnswers: " + e.getMessage());
            assertTrue(false);
        }
    }

    @Test
    public void testIncorrectPhrase(){
        try{
            QuestionsData questionsData = new QuestionsData(path);
            RequestHandler requestHandler = new RequestHandler(questionsData);
            User user = new User();
            ArrayList<String> result = requestHandler.getAnswerByCommandAndRequest(Command.NONE, "none", user);
            assertThat(result, hasItem(PhrasesHandler.getUnknownPhrase()));
        }
        catch (IOException e){
            System.out.println("Wrong testIncorrectPhrase: " + e.getMessage());
            assertTrue(false);
        }
    }
}
