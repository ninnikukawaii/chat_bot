package logic;

import logic.enums.UserState;

public class User {
    private UserState state;
    private Question lastQuestion;
    private Long id;

    private boolean newQuestion = false;

    public User(Long id) {
        state = UserState.START;
        lastQuestion = null;
        this.id = id;
    }

    public UserState getState(){
        return state;
    }

    public void setState(UserState userState){
        state = userState;
    }

    public Question getLastQuestion() {
        return lastQuestion;
    }

    public void setLastQuestion(Question lastQuestion) {
        this.lastQuestion = lastQuestion;
    }

    public Long getId() {
        return id;
    }

    public boolean isNewQuestion() {
        return newQuestion;
    }

    public void needNewQuestion(boolean newQuestion) {
        this.newQuestion = newQuestion;
    }
}
