package logic.handlers;

import logic.enums.Command;

public class RequestHandler {
    public Command tryCommandRecognition(String usersRequest) {
        switch (usersRequest) {
            case PhrasesHandler.getHelpPhrase():

            default:
                return Command.none;
        }
    }
}
