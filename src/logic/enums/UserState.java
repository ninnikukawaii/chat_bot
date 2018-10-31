package logic.enums;

import logic.handlers.PhrasesHandler;

public enum UserState {
    START {
        @Override
        public String getHelp() {
            return PhrasesHandler.getStartHelp();
        }
    },
    DIALOG {
        @Override
        public String getHelp() {
            return PhrasesHandler.getDialogHelp();
        }
    },
    QUIZ {
        @Override
        public String getHelp() {
            return PhrasesHandler.getQuizHelp();
        }
    },
    EXIT {
        @Override
        public String getHelp() {
            return null;
        }
    };

    public abstract String getHelp();
}
