import logic.Input;
import logic.MainLoop;
import logic.Output;

public class Main {

    public static void main(String[] args) {
        MainLoop mainLoop = new MainLoop(new Input(), new Output());
        mainLoop.startLoop();
    }
}
