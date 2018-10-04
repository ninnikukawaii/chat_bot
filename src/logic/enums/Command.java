package logic.enums;

import logic.handlers.PhrasesHandler;

import java.io.Serializable;

public enum Command implements Serializable {
    HELP (PhrasesHandler.getHelpCommand()),
    EXIT (PhrasesHandler.getExitCommand()),
    QUIZ (PhrasesHandler.getQuizCommand()),
    GIVE_UP (PhrasesHandler.getGiveUpCommand()),
    REPEAT_QUESTION (PhrasesHandler.getRepeatQuestionCommand()),
    NONE (PhrasesHandler.getNoneCommand());

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public static Command valueByString(String value){
        value = value.toLowerCase();
        for (Command command : Command.values()) {
            if (value.equalsIgnoreCase(command.toString().toLowerCase())) {
                return command;
            }
        }
        return NONE;
    }

    @Override
    public String toString() {
        return name;
    }
}
