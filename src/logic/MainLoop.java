package logic;

import logic.enums.Command;
import logic.handlers.CommandHandler;
import logic.handlers.PhrasesHandler;
import logic.handlers.RequestHandler;
import logic.interfaces.IInput;
import logic.interfaces.IOutput;

public class MainLoop {
    private IInput mInput;
    private IOutput mOutput;
    private User mUser;

    private RequestHandler mRequestHandler;
    private CommandHandler mCommandHandler;

    public MainLoop(IInput input, IOutput output){
        mInput = input;
        mOutput = output;
        mUser = new User();

        mRequestHandler = new RequestHandler();
        mCommandHandler = new CommandHandler();
    }

    public void startLoop(){

        while (!mUser.isExit()) {
            Request request = mInput.getRequest();
            Command command = mRequestHandler.tryCommandRecognition(request.getUsersRequest());
            String message = mCommandHandler.getAnswerByCommand(command, mUser);
            mOutput.tellUser(message);
        }

        String endPhrase = PhrasesHandler.getEndPhrase();
        mOutput.tellUser(endPhrase);
    }
}
