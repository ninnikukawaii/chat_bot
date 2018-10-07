package logic;

import logic.enums.Command;
import logic.enums.UserState;
import logic.exception.FileReadException;
import logic.handlers.PhrasesHandler;
import logic.handlers.RequestHandler;
import logic.interfaces.Input;
import logic.interfaces.Output;

import java.io.IOException;
import java.util.ArrayList;

public class MainLoop {

    private RequestHandler mRequestHandler;

    public MainLoop() throws FileReadException {
        QuestionsData questionsData = new QuestionsData("questions.txt");

        mRequestHandler = new RequestHandler(questionsData);
    }

    public void startLoop(Input input, Output output){
        User user = new User();

        ArrayList<String> messages = new ArrayList<>();
        messages.add(PhrasesHandler.getStartPhrase());
        output.tellUser(messages);

        while (user.getState() != UserState.EXIT) {
            Request request = input.getRequest();
            Command command = Command.valueByString(request.getUsersRequest());
            messages = mRequestHandler.getAnswerByCommandAndRequest(command, request.getUsersRequest(), user);
            output.tellUser(messages);
        }
    }
}
