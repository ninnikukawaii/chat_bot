package logic.handlers;

import logic.Question;
import logic.interfaces.QuestionsData;
import logic.User;
import logic.enums.Command;
import logic.enums.UserState;
import logic.interfaces.Processing;

import java.util.ArrayList;
import java.util.List;

public class RequestHandler implements Processing {
    private QuestionsData mQuestionData;

    public RequestHandler(QuestionsData questionsData){
        mQuestionData = questionsData;
    }

    public List<String> getAnswerByCommandAndRequest(Command command, String request, User user) {
        List<String> answer = new ArrayList<>();

        if (command != null) {
            answer.addAll(command.requestProcessing(request, user));
        }
        else {
            answer.addAll(requestProcessing(request, user));
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
        if (user.isNewQuestion()) {
            user.setNewQuestion(false);

            answer.add(getQuestion(user));
        }
    }

    private String getQuestion(User user) {
        Question question = mQuestionData.getQuestion();
        user.setLastQuestion(question);
        return PhrasesHandler.getQuestionOnQuiz(question.getQuestion());
    }

    @Override
    public ArrayList<String> requestProcessing(String request, User user) {
        ArrayList<String> answer = new ArrayList<>();
        if (user.getState() == UserState.QUIZ) {
            if (request.toLowerCase().equals(user.getLastQuestion().getAnswer().toLowerCase())) {
                answer.add(PhrasesHandler.getCorrectAnswerPhrase());
                answer.add(getQuestion(user));
            }
            else {
                answer.add(PhrasesHandler.getIncorrectAnswerPhrase());
            }
        }
        return answer;
    }
}
