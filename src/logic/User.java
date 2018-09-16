package logic;

import logic.enums.UserState;

public class User {
    private UserState mState;
    private String mLastMessage;
    private boolean isExit;

    public User() {
        mState = UserState.start;
        mLastMessage = "";
        isExit = false;
    }

    public UserState getState(){
        return mState;
    }

    public String getLastMessage() {
        return mLastMessage;
    }

    public boolean isExit() {
        return isExit;
    }
}
