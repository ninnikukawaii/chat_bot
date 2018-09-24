package logic;

import logic.enums.Command;
import logic.enums.UserState;
import logic.handlers.PhrasesHandler;
import logic.handlers.RequestHandler;
import logic.interfaces.Input;
import logic.interfaces.Output;

import java.io.IOException;
import java.util.ArrayList;

public class MainLoop {

    private RequestHandler mRequestHandler;

    public MainLoop() {
        try {
            QuestionsData questionsData = new QuestionsData("questions.txt");

            mRequestHandler = new RequestHandler(questionsData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startLoop(Input input, Output output){
        User user = new User();

        ArrayList<String> messages = new ArrayList<>();
        messages.add(PhrasesHandler.getStartPhrase());
        output.tellUser(messages);

        while (user.getState() != UserState.exit) {
            Request request = input.getRequest();
            Command command = mRequestHandler.tryCommandRecognition(request.getUsersRequest());
            messages = mRequestHandler.getAnswerByCommandAndRequest(command, request.getUsersRequest(), user);
            output.tellUser(messages);
        }
    }
}
