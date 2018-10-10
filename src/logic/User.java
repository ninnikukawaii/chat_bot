package logic;

import logic.enums.UserState;

public class User {
    private UserState state;
    private Question lastQuestion;
    private Long id;

    public User(Long id) {
        state = UserState.DIALOG;
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
}
