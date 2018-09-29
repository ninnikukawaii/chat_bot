package logic.enums;

import logic.handlers.PhrasesHandler;

import java.io.Serializable;

public enum Command implements Serializable {
    HELP {
        @Override
        public String toString(){
            return PhrasesHandler.getHelpCommand();
        }
    },
    EXIT{
        @Override
        public String toString(){
            return PhrasesHandler.getExitCommand();
        }
    },
    QUIZ{
        @Override
        public String toString(){
            return PhrasesHandler.getQuizCommand();
        }
    },
    GIVE_UP {
        @Override
        public String toString() {
            return PhrasesHandler.getGiveUpCommand();
        }
    },
    REPEAT_QUESTION {
        @Override
        public String toString() {
            return PhrasesHandler.getRepeatQuestionCommand();
        }
    },
    NONE;

    public static Command valueByString(String value){
        value = value.toLowerCase();
        if(value.equalsIgnoreCase(HELP.toString().toLowerCase())) {
            return Command.HELP;
        }
        else if (value.equalsIgnoreCase(EXIT.toString().toLowerCase())) {
            return Command.EXIT;
        }
        else if (value.equalsIgnoreCase(QUIZ.toString().toLowerCase())) {
            return Command.QUIZ;
        }
        else if (value.equalsIgnoreCase(GIVE_UP.toString().toLowerCase())) {
            return Command.GIVE_UP;
        }
        else if (value.equalsIgnoreCase(REPEAT_QUESTION.toString().toLowerCase())) {
            return Command.REPEAT_QUESTION;
        }
        else {
            return Command.NONE;
        }
    }
}
