package logic;

import logic.enums.Command;
import logic.enums.UserState;
import logic.exception.FileReadException;
import logic.handlers.RequestHandler;
import logic.interfaces.Input;
import logic.interfaces.Output;
import logic.interfaces.Processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainLoop {

    private RequestHandler requestHandler;
    private Map<Long, User> users = new HashMap<>();

    public MainLoop() throws FileReadException {
        QuestionsData questionsData = new QuestionsData("resources/questions.txt");

        requestHandler = new RequestHandler(questionsData);
    }

    public void startLoop(Input input, Output output) throws InterruptedException {
        while (true) {
            Thread.sleep(10);

            Request request = input.getRequest();
            if (request == null) {
                continue;
            }

            Long id = request.getUserId();

            if (!users.containsKey(request.getUserId())) {
                users.put(id, new User(id));
            }
            User user = users.get(id);

            Command command = Command.valueByString(request.getUsersRequest());
            Processor processor = (command == null ? new RequestProcessor(request.getUsersRequest()) : command);

            List<String> messages = requestHandler.getAnswerByProcessor(processor, user);
            output.tellUser(messages, user);

            if (user.getState() == UserState.EXIT) {
                users.remove(id);
            }
        }
    }
}
