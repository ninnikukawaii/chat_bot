import logic.ConsoleOutput;
import logic.MainLoop;
import logic.ScannerInput;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MainLoop mainLoop = new MainLoop();
        mainLoop.startLoop(new ScannerInput(), new ConsoleOutput());
    }
}
