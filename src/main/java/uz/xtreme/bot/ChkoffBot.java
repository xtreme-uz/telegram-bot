package uz.xtreme.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Author: Rustambekov Avazbek
 * Date: 01/06/2020
 * Time: 01:28
 */
public class ChkoffBot extends TelegramLongPollingBot {

    private final Utils utils;

    public ChkoffBot() {
        utils = new Utils();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            File file = utils.text2voice(update.getMessage().getText());
            SendAudio audioMessage = new SendAudio()
                    .setChatId(update.getMessage().getChatId())
                    .setAudio(file);
            try {
                execute(audioMessage);
                Files.delete(Path.of(file.getPath()));
            } catch (TelegramApiException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "ChkoffBot";
    }

    @Override
    public String getBotToken() {
        return "1001664336:AAHhxf9N3p_PVhEoM2fGKDNOk0Y5zA5mm1c";
    }
}
