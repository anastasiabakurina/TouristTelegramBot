package touristTelegramBot.bots;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import touristTelegramBot.repo.CitiesRepository;

public class TelegramBot extends TelegramLongPollingBot {

    boolean isRunning = false;
    private CitiesRepository citiesRepository;

    public TelegramBot(DefaultBotOptions options, CitiesRepository citiesRepository) {
        super(options);
        this.citiesRepository = citiesRepository;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String text = message.getText().toLowerCase();

        if (text.equals("/start")) {
            isRunning = true;
            sendMsg(message, "Привет! Я расскажу тебе, что можно посмотреть/сделать в другом городе :) Введи город: ");
        }

        if (isRunning && !"/start".equals(text)) {
            if (citiesRepository.findCitiesNames().contains(text)) {
                sendMsg(message, citiesRepository.messageOfCity(text));
            } else {
                sendMsg(message, "Такого города нет в базе!");
            }
        }
    }

    private void sendMsg(Message msg, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(msg.getChatId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "InfoForTouristAboutCity_bot";
    }

    @Override
    public String getBotToken() {
        return "1114481172:AAE4CzcqSIiw71AHpEdL0XW6yMMynwa_6vw";
    }
}