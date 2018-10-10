package logic;

import logic.interfaces.Output;

import java.util.ArrayList;

public class ConsoleOutput implements Output {
    @Override
    public void tellUser(ArrayList<String> messages, User user) {
        for (String line : messages) {
            System.out.println(line);
        }
    }
}