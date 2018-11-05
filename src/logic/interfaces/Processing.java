package logic.interfaces;

import logic.User;

import java.util.List;

public interface Processing {
    List<String> applicationProcessing(String request, User user);
}
