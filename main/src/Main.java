import com.wolfram.alpha.WAException;
import logic.DataBaseManager;
import logic.MainLoop;
import logic.exception.DataBaseException;
import logic.exception.FileReadException;
import logic.MathProcessor;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws FileReadException, TelegramApiException, InterruptedException, IOException, DataBaseException, WAException {
        Properties props = new Properties();
        props.load(new FileInputStream("resources/config.properties"));

        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();
        TelegramBot bot = new TelegramBot(props);
        botapi.registerBot(bot);

        DataBaseManager dataBaseManager = DataBaseManager.getInstance();

        MathProcessor mathProcessor = new MathProcessor(props);

        MainLoop mainLoop = new MainLoop(dataBaseManager, mathProcessor);
        mainLoop.startLoop(bot, bot);
    }
}
