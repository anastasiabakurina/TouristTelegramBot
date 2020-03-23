package touristTelegramBot.bots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import touristTelegramBot.repo.CitiesRepository;

import javax.annotation.PostConstruct;

@Component
public class StartBot {

    @Autowired
    private CitiesRepository citiesRepository;

    @PostConstruct
    public void run() {
        try {
            ApiContextInitializer.init();
            TelegramBotsApi telegram = new TelegramBotsApi();
            DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
            telegram.registerBot(new TelegramBot(options, citiesRepository));
        } catch (
                TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
