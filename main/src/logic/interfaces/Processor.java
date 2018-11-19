package logic.interfaces;

import logic.User;

import java.util.List;

public interface Processor {
    List<String> requestProcessing(User user);
}
