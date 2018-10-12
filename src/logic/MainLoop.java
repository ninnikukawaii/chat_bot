package logic;

import logic.enums.Command;
import logic.enums.UserState;
import logic.exception.FileReadException;
import logic.handlers.PhrasesHandler;
import logic.handlers.RequestHandler;
import logic.interfaces.Input;
import logic.interfaces.Output;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainLoop {

    private RequestHandler requestHandler;
    private HashMap<Long, User> users = new HashMap<>();

    public MainLoop() throws FileReadException {
        QuestionsData questionsData = new QuestionsData("questions.txt");

        requestHandler = new RequestHandler(questionsData);
    }

    public void startLoop(Input input, Output output) throws InterruptedException {
        while (true) {
            Thread.sleep(10);
            
            if (input.isNoRequests()) {
                continue;
            }
            Request request = input.getRequest();

            Long id = request.getUserId();

            if (!users.containsKey(request.getUserId())) {
                users.put(id, new User(id));
            }
            User user = users.get(id);

            Command command = Command.valueByString(request.getUsersRequest());
            ArrayList<String> messages =
                    requestHandler.getAnswerByCommandAndRequest(command, request.getUsersRequest(), user);
            output.tellUser(messages, user);

            if (user.getState() == UserState.EXIT) {
                users.remove(id);
            }
        }
    }
}
