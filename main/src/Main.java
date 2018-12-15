import com.wolfram.alpha.WAException;
import logic.exception.DataBaseException;
import logic.exception.FileReadException;
import logic.handlers.MathHandler;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws FileReadException, TelegramApiException, InterruptedException, IOException, DataBaseException, WAException {
        /*ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();

        Properties props = new Properties();
        props.load(new FileInputStream("resources/config.properties"));
        TelegramBot bot = new TelegramBot(props);

        botapi.registerBot(bot);

        DataBaseManager dataBaseManager = DataBaseManager.getInstance();

        MainLoop mainLoop = new MainLoop(dataBaseManager);
        mainLoop.startLoop(bot, bot);*/


        Properties props = new Properties();
        props.load(new FileInputStream("resources/config.properties"));
        MathHandler handler = new MathHandler(props);
        String input = "!X || Y";

        System.out.println(handler.getResponse(input));
        /*WAEngine engine = new WAEngine();
        String input = "!X || Y";

        // These properties will be set in all the WAQuery objects created from this WAEngine.
        engine.setAppID("T4W973-VHYVW6J7UA");
        engine.addFormat("plaintext");

        // Create the query.
        WAQuery query = engine.createQuery();

        // Set properties of the query.
        query.setInput(input);

        try {
            // For educational purposes, print out the URL we are about to send:
            System.out.println("Query URL:");
            System.out.println(engine.toURL(query));
            System.out.println("");

            // This sends the URL to the Wolfram|Alpha server, gets the XML result
            // and parses it into an object hierarchy held by the WAQueryResult object.
            WAQueryResult queryResult = engine.performQuery(query);

            if (queryResult.isError()) {
                System.out.println("Query error");
                System.out.println("  error code: " + queryResult.getErrorCode());
                System.out.println("  error message: " + queryResult.getErrorMessage());
            } else if (!queryResult.isSuccess()) {
                System.out.println("Query was not understood; no results available.");
            } else {
                // Got a result.
                System.out.println("Successful query. Pods follow:\n");
                for (WAPod pod : queryResult.getPods()) {
                    if (!pod.isError()) {
                        System.out.println(pod.getTitle());
                        System.out.println("------------");
                        for (WASubpod subpod : pod.getSubpods()) {
                            for (Object element : subpod.getContents()) {
                                if (element instanceof WAPlainText) {
                                    System.out.println(((WAPlainText) element).getText());
                                    System.out.println("");
                                }
                            }
                        }
                        System.out.println("");
                    }
                }
                // We ignored many other types of Wolfram|Alpha output, such as warnings, assumptions, etc.
                // These can be obtained by methods of WAQueryResult or objects deeper in the hierarchy.
            }
        } catch (WAException e) {
            e.printStackTrace();
        }*/
    }
}
