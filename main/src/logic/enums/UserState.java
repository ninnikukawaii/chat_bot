package logic.enums;

import logic.handlers.PhrasesHandler;


public enum UserState {
    START(PhrasesHandler.getStartHelp()),
    DIALOG(PhrasesHandler.getDialogHelp()),
    QUIZ(PhrasesHandler.getQuizHelp()),
    MATH_MODE(PhrasesHandler.getMathModeHelp()),
    EXIT(null);

    private String description;

    UserState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
