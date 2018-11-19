package logic;

import logic.enums.UserState;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User {
    @Enumerated(EnumType.STRING)
    private UserState state;
    private Question lastQuestion;
    @Id
    private Long id;

    public static final Long defaultId = 1L;

    transient private boolean needNewQuestion = false;

    public User(Long id) {
        initialize();
        this.id = id;
    }

    public User() {
        initialize();
        id = defaultId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return state == user.state &&
                Objects.equals(lastQuestion, user.lastQuestion) &&
                Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, lastQuestion, id);
    }
}
