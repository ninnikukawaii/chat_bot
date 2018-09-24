import logic.ConsoleOutput;
import logic.MainLoop;
import logic.ScannerInput;

public class Main {
    public static void main(String[] args) {
        MainLoop mainLoop = new MainLoop();
        mainLoop.startLoop(new ScannerInput(), new ConsoleOutput());
    }
}
