package logic;

import logic.enums.Command;
import logic.enums.UserState;
import logic.exception.DataBaseException;
import logic.exception.FileReadException;
import logic.handlers.RequestHandler;
import logic.interfaces.Input;
import logic.interfaces.Output;
import logic.interfaces.Processor;

import java.util.List;

public class MainLoop {

    private RequestHandler requestHandler;
    private DataBaseManager dataBaseManager;
    private MathProcessor mathProcessor;

    public MainLoop(DataBaseManager dataBaseManager, MathProcessor mathProcessor) throws FileReadException {
        QuestionsData questionsData = new QuestionsData("resources/questions.txt");

        requestHandler = new RequestHandler(questionsData);
        this.dataBaseManager = dataBaseManager;
        this.mathProcessor = mathProcessor;
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
            User user = dataBaseManager.getUser(id);

            if (user.getState() == UserState.EXIT) {
                user = dataBaseManager.recreateUser(user.getId());
            }

            Command command = Command.valueByString(request.getUsersRequest());
            Processor processor;

            if (command != null) {
                processor = command;
            }
            else if (user.getState() == UserState.MATH_MODE) {
                processor = mathProcessor;
            }
            else {
                processor = new RequestProcessor(request.getUsersRequest());
            }

            List<String> messages = requestHandler.getAnswerByProcessor(processor, user);
            output.tellUser(messages, user);

            dataBaseManager.endTransaction();
        }
    }
}
