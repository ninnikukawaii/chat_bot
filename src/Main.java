import logic.ConsoleOutput;
import logic.MainLoop;
import logic.ScannerInput;
import logic.exception.FileReadException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileReadException {
        MainLoop mainLoop = new MainLoop();
        mainLoop.startLoop(new ScannerInput(), new ConsoleOutput());
    }
}
