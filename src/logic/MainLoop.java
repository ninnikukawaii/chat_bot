package logic;

import logic.enums.Command;
import logic.enums.UserState;
import logic.handlers.PhrasesHandler;
import logic.handlers.RequestHandler;
import logic.interfaces.IInput;
import logic.interfaces.IOutput;

public class MainLoop {
    private IInput mInput;
    private IOutput mOutput;
    private User mUser;

    private RequestHandler mRequestHandler;

    private QuestionsData mQuestionsData;

    public MainLoop(IInput input, IOutput output){
        mInput = input;
        mOutput = output;
        mUser = new User();

        mQuestionsData = new QuestionsData("questions.txt");

        mRequestHandler = new RequestHandler(mQuestionsData);
    }

    public void startLoop(){
        mOutput.tellUser(PhrasesHandler.getStartPhrase());

        while (mUser.getState() != UserState.exit) {
            Request request = mInput.getRequest();
            Command command = mRequestHandler.tryCommandRecognition(request.getUsersRequest());
            String message = mRequestHandler.getAnswerByCommandAndRequest(command, request.getUsersRequest(), mUser);
            mOutput.tellUser(message);
        }
    }
}
