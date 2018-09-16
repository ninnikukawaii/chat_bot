package logic;

import logic.interfaces.IInput;

import java.util.Scanner;

public class Input implements IInput {
    private Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    @Override
    public Request getRequest() {
        String request = scanner.nextLine();
        return new Request(request);
    }
}
