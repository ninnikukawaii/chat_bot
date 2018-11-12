package logic.enums;

import logic.handlers.PhrasesHandler;

import java.util.function.Supplier;

public enum UserState {
    START(PhrasesHandler.getStartHelp()) {
    },
    DIALOG(PhrasesHandler.getDialogHelp()) {
    },
    QUIZ(PhrasesHandler.getQuizHelp()) {
    },
    EXIT(null) {
    };

    private String description;

    UserState(String description) {
        this.description = description;
    }

    public String getHelp() {
        return description;
    };
}
