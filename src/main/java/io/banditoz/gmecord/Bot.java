package io.banditoz.gmecord;

import io.banditoz.gmecord.events.DiscordMessageEvent;
import io.banditoz.gmecord.tasks.ActivityTimerTask;
import io.banditoz.gmecord.tasks.MentionablesTimerTask;
import io.banditoz.gmecord.web.WebServer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Bot {
    private static JDA jda;
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor(logger::debug).setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();
    private static HashMap<String, String> mentionableGroupme;
    private static HashMap<String, String> mentionableDiscord;

    public Bot() {
        Settings settings = SettingsManager.getInstance().getSettings();
        try {
            jda = JDABuilder.createLight(settings.getDiscordToken()).enableIntents(GatewayIntent.GUILD_MEMBERS).build();
            jda.awaitReady();
        } catch (Exception ex) {
            logger.error("Could not login! Exiting safely...", ex);
            System.exit(1);
        }
        jda.addEventListener(new DiscordMessageEvent());
        WebServer ws = new WebServer();

        ws.start();
        ws.setName("WebServer");

        // TODO: Fix the race condition here. If a message is sent and it hasn't gotten the mentionables, it will NPE.
        Timer mentionablesTimer = new Timer();
        mentionablesTimer.schedule(new MentionablesTimerTask(), 0L, TimeUnit.HOURS.toMillis(1));

        Timer pingMeasurementTimer = new Timer();
        pingMeasurementTimer.schedule(new ActivityTimerTask(), 0L, TimeUnit.MINUTES.toMillis(1));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> logger.info("Bot is now shutting down.")));
    }

    public static JDA getJda() {
        return jda;
    }

    public static HashMap<String, String> getMentionableGroupme() {
        return mentionableGroupme;
    }

    public static void setMentionableGroupme(HashMap<String, String> mentionableGroupme) {
        Bot.mentionableGroupme = mentionableGroupme;
    }

    public static HashMap<String, String> getMentionableDiscord() {
        return mentionableDiscord;
    }

    public static void setMentionableDiscord(HashMap<String, String> mentionableDiscord) {
        Bot.mentionableDiscord = mentionableDiscord;
    }

    public static OkHttpClient getOkHttpClient() {
        return client;
    }
}
