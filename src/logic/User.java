package logic;

import logic.enums.UserState;

public class User {
    private UserState mState;
    private Question mLastQuestion;

    public User() {
        mState = UserState.start;
        mLastQuestion = null;
    }

    public UserState getState(){
        return mState;
    }

    public void setState(UserState userState){
        mState = userState;
    }

    public Question getLastQuestion() {
        return mLastQuestion;
    }

    public void setLastQuestion(Question lastQuestion) {
        mLastQuestion = lastQuestion;
    }
}
