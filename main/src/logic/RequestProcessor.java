package logic;

import logic.enums.UserState;
import logic.handlers.PhrasesHandler;
import logic.interfaces.Processor;

import java.util.ArrayList;
import java.util.List;

public class RequestProcessor implements Processor {
    private String request;

    public RequestProcessor(String request) {
        this.request = request;
    }

    public List<String> requestProcessing(User user) {
        ArrayList<String> answer = new ArrayList<>();
        if (user.getState() == UserState.QUIZ) {
            if (request.toLowerCase().equals(user.getLastQuestion().getAnswer().toLowerCase())) {
                answer.add(PhrasesHandler.getCorrectAnswerPhrase());
                user.setNeedNewQuestion(true);
            }
            else {
                answer.add(PhrasesHandler.getIncorrectAnswerPhrase());
            }
        }
        return answer;
    }
}
