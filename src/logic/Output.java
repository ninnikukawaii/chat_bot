package logic;

import logic.interfaces.IOutput;

public class Output implements IOutput {
    @Override
    public void tellUser(String message) {
        System.out.print(message + '\n');
    }
}