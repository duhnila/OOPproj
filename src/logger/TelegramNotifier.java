package logger;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TelegramNotifier extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
    }

    public void notifyAdmin(String login, String id, boolean isReg) {
        SendMessage response = new SendMessage();
        String message;

        if (isReg) {
            message = String.format("User %s[%s] has registered", login, id);
        }
        else {
            message = String.format("User %s[%s] authorized", login, id);
        }

        String chatIDs[] = new String[]{"819151572", "864239607", "5557586476"};
        for (int i = 0; i < chatIDs.length; i++) {
            System.out.println(chatIDs[i]);
            String chatId = chatIDs[i];
            response.setChatId(chatId);
            response.setText(message);
            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String getBotUsername() {
        // TODO
        return "SE2213NotificationBot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "6017114330:AAFRXmsWkzIsclJkFDBC82SEOI1V3278wEs";
    }
}
