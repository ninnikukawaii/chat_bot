package logic;
import logic.interfaces.Input;
import java.util.Scanner;

public class ScannerInput implements Input {
    private Scanner scanner;

    public ScannerInput() {
        scanner = new Scanner(System.in);
    }

    @Override
    public Request getRequest() {
        String request = scanner.nextLine();
        return new Request(request, User.defaultId);
    }

}