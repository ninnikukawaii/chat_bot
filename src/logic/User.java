package logic;

import logic.enums.UserState;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    private UserState state;
    private Question lastQuestion;
    @Id
    private Long id;

    private boolean needNewQuestion = false;

    public User(Long id) {
        initialize();
        this.id = id;
    }

    public User() {
        initialize();
        id = 0L;
    }

    private void initialize() {
        state = UserState.START;
        lastQuestion = null;
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

    public boolean getNeedNewQuestion() {
        return needNewQuestion;
    }

    public void setNeedNewQuestion(boolean newQuestion) {
        this.needNewQuestion = newQuestion;
    }
}
