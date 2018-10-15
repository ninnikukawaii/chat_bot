import logic.Request;
import logic.User;
import logic.interfaces.Input;
import logic.interfaces.Output;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class TelegramBot extends TelegramLongPollingBot implements Input, Output {
    private ArrayDeque<Request> requests = new ArrayDeque<>();

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();
        requests.add(new Request(msg.getText(), msg.getChatId()));
    }

    @Override
    public String getBotUsername() {
        return "S_E_L_L_E_R_BOT";
    }

    @Override
    public String getBotToken() {
        return "601890516:AAHpRCZLvBgq8cOvtwkupUfyxVD1-lEOncg";
    }

    @Override
    public Request getRequest() {
        if (requests.isEmpty())
            return null;
        return requests.remove();
    }

    @Override
    public boolean isNoRequests() {
        return requests.isEmpty();
    }

    @Override
    public void tellUser(ArrayList<String> messages, User user) {
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
