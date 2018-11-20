package logic;

import logic.enums.Command;
import logic.enums.UserState;
import logic.exception.DataBaseException;
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
    private DataBaseManager dataBaseManager;

    public MainLoop(DataBaseManager dataBaseManager) throws FileReadException {
        QuestionsData questionsData = new QuestionsData("resources/questions.txt");

        requestHandler = new RequestHandler(questionsData);
        this.dataBaseManager = dataBaseManager;
    }

    public void startLoop(Input input, Output output) throws InterruptedException, DataBaseException {
        while (true) {
            Thread.sleep(10);

            Request request = input.getRequest();
            if (request == null) {
                continue;
            }

            Long id = request.getUserId();

            dataBaseManager.beginTransaction();
            User user = dataBaseManager.createNewUser(id);

            if (user.getState() == UserState.EXIT) {
                user = new User(user.getId());
            }
            dataBaseManager.updateDataAboutUser(user);

            Command command = Command.valueByString(request.getUsersRequest());
            Processor processor = (command == null ? new RequestProcessor(request.getUsersRequest()) : command);

            List<String> messages = requestHandler.getAnswerByProcessor(processor, user);
            output.tellUser(messages, user);

            dataBaseManager.updateDataAboutUser(user);
            dataBaseManager.endTransaction();
        }
    }
}
