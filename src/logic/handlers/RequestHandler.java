package logic.handlers;

import logic.Question;
import logic.QuestionsData;
import logic.User;
import logic.enums.Command;
import logic.enums.UserState;

import java.util.ArrayList;

public class RequestHandler {
    private QuestionsData mQuestionData;

    public RequestHandler(QuestionsData questionsData){
        mQuestionData = questionsData;
    }

    public ArrayList<String> getAnswerByCommandAndRequest(Command command, String request, User user) {
        ArrayList<String> answer = new ArrayList<>();

        if (command == Command.EXIT && user.getState() == UserState.DIALOG) {
            user.setState(UserState.EXIT);

            answer.add(PhrasesHandler.getEndPhrase());

            return answer;
        }
        else if (command == Command.EXIT && user.getState() == UserState.QUIZ) {
            user.setState(UserState.DIALOG);

            answer.add(PhrasesHandler.getEndQuizPhrase());

            return answer;
        }
        else if (command == Command.HELP && user.getState() == UserState.DIALOG) {
            answer.add(PhrasesHandler.getHelp());

            return answer;
        }
        else if (command == Command.QUIZ && user.getState() == UserState.DIALOG) {
            user.setState(UserState.QUIZ);

            answer.add(PhrasesHandler.getStartQuizPhrase());
            answer.add(getQuestion(user));

            return answer;
        }
        else if (command == Command.HELP && user.getState() == UserState.QUIZ) {
            answer.add(PhrasesHandler.getQuizHelp());
            return answer;
        }
        else if (command == Command.NONE && user.getState() == UserState.QUIZ) {
            if (request.toLowerCase().equals(user.getLastQuestion().getAnswer().toLowerCase())) {
                answer.add(PhrasesHandler.getCorrectAnswerPhrase());
                answer.add(getQuestion(user));

                return answer;
            }
            else {
                answer.add(PhrasesHandler.getIncorrectAnswerPhrase());

                return answer;
            }
        }
        else if (command == Command.GIVE_UP && user.getState() == UserState.QUIZ) {
            answer.add(PhrasesHandler.getCorrectAnswerInQuiz(user.getLastQuestion().getAnswer()));
            answer.add(getQuestion(user));

            return answer;
        }
        else if (command == Command.REPEAT_QUESTION && user.getState() == UserState.QUIZ) {
            answer.add(user.getLastQuestion().getQuestion());

            return answer;
        }

        answer.add(PhrasesHandler.getUnknownPhrase());

        return answer;
    }

    private String getQuestion(User user) {
        Question question = mQuestionData.getQuestion();
        user.setLastQuestion(question);
        return PhrasesHandler.getQuestionOnQuiz(question.getQuestion());
    }
}
