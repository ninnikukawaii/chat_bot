import logic.ConsoleOutput;
import logic.MainLoop;
import logic.ScannerInput;
import logic.exception.FileReadException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileReadException, TelegramApiException {
        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();

        TelegramBot bot = new TelegramBot();

        botapi.registerBot(bot);

        MainLoop mainLoop = new MainLoop();
        mainLoop.startLoop(bot, bot);
    }
}
