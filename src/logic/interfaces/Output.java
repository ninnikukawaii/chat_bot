package logic.interfaces;

import logic.User;

import java.util.ArrayList;

public interface Output {
    void tellUser(ArrayList<String> messages, User user);
}
