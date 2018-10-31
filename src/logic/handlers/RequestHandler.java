package logic.handlers;

import logic.Question;
import logic.QuestionsData;
import logic.User;
import logic.enums.Command;
import logic.enums.UserState;

import java.util.ArrayList;
import java.util.List;

public class RequestHandler {
    private QuestionsData mQuestionData;

    public RequestHandler(QuestionsData questionsData){
        mQuestionData = questionsData;
    }

    public List<String> getAnswerByCommandAndRequest(Command command, String request, User user) {
        List<String> answer = new ArrayList<>();

        if (command != null) {
            answer.addAll(command.commandProcessing(user));
        }
        else {
            requestProcessing(request, user, answer);
        }

        usersFlagsProcessing(user, answer);

        if (answer.isEmpty()) {
            if (user.getState() == UserState.START) {
                user.setState(UserState.EXIT);
            }
            answer.add(PhrasesHandler.getUnknownPhrase());
        }

        return answer;
    }

    private void usersFlagsProcessing(User user, List<String> answer) {
        if (user.isGetNewQuestion()) {
            user.setGetNewQuestion(false);

            answer.add(getQuestion(user));
        }
    }

    private void requestProcessing(String request, User user, List<String> answer) {
        if (user.getState() == UserState.QUIZ) {
            if (request.toLowerCase().equals(user.getLastQuestion().getAnswer().toLowerCase())) {
                answer.add(PhrasesHandler.getCorrectAnswerPhrase());
                answer.add(getQuestion(user));
            }
            else {
                answer.add(PhrasesHandler.getIncorrectAnswerPhrase());
            }
        }
    }

    private String getQuestion(User user) {
        Question question = mQuestionData.getQuestion();
        user.setLastQuestion(question);
        return PhrasesHandler.getQuestionOnQuiz(question.getQuestion());
    }
}
