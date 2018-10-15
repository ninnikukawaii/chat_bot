package logic.interfaces;

import logic.User;

import java.util.ArrayList;
import java.util.List;

public interface Output {
    void tellUser(List<String> messages, User user);
}
