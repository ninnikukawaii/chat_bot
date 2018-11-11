import logic.Request;
import logic.User;
import logic.interfaces.Input;
import logic.interfaces.Output;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Properties;

public class TelegramBot extends TelegramLongPollingBot implements Input, Output {
    private ArrayDeque<Request> requests = new ArrayDeque<>();
    private String botUsername;
    private String botToken;

    public TelegramBot(Properties props) {
        this.botUsername = props.getProperty("botUsername");
        this.botToken = props.getProperty("botToken");
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();
        requests.add(new Request(msg.getText(), msg.getChatId()));
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public Request getRequest() {
        if (requests.isEmpty())
            return null;
        return requests.remove();
    }

    @Override
    public void tellUser(List<String> messages, User user) {
        String answer = String.join("\n", messages);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(user.getId());
        sendMessage.setText(answer);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
