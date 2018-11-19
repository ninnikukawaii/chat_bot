package logic.handlers;

import logic.Question;
import logic.interfaces.QuestionsData;
import logic.User;
import logic.enums.UserState;
import logic.interfaces.Processor;

import java.util.ArrayList;
import java.util.List;

public class RequestHandler {
    private QuestionsData mQuestionData;

    public RequestHandler(QuestionsData questionsData){
        mQuestionData = questionsData;
    }

    public List<String> getAnswerByProcessor(Processor processor, User user) {

        List<String> answer = new ArrayList<>(processor.requestProcessing(user));

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
        if (user.getNeedNewQuestion()) {
            user.setNeedNewQuestion(false);

            answer.add(getQuestion(user));
        }
    }

    private String getQuestion(User user) {
        Question question = mQuestionData.getQuestion();
        user.setLastQuestion(question);
        return PhrasesHandler.getQuestionOnQuiz(question.getQuestion());
    }
}
