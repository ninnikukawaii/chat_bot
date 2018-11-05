package logic.interfaces;

import logic.User;

import java.util.List;

public interface Processing {
    List<String> requestProcessing(String request, User user);
}
