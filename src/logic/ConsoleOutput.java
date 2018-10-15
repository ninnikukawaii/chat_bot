package logic;

import logic.interfaces.Output;

import java.util.ArrayList;
import java.util.List;

public class ConsoleOutput implements Output {
    @Override
    public void tellUser(List<String> messages, User user) {
        for (String line : messages) {
            System.out.println(line);
        }
    }
}