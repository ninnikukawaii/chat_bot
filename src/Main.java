import logic.MainLoop;
import logic.exception.FileReadException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws FileReadException, TelegramApiException, InterruptedException, IOException {
        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();

        Properties props = new Properties();
        props.load(new FileInputStream("config.properties"));
        TelegramBot bot = new TelegramBot(props);

        botapi.registerBot(bot);

        MainLoop mainLoop = new MainLoop();
        mainLoop.startLoop(bot, bot);
    }
}
