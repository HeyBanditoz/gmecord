package io.banditoz.gmecord;

import io.banditoz.gmecord.events.DiscordMessageEvent;
import io.banditoz.gmecord.tasks.ActivityTimerTask;
import io.banditoz.gmecord.tasks.MentionablesTimerTask;
import io.banditoz.gmecord.web.WebServer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import okhttp3.OkHttpClient;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Bot {
    private static JDA jda;
    private static HashMap<String, String> mentionableGroupme;
    private static HashMap<String, String> mentionableDiscord;
    public static final OkHttpClient client = new OkHttpClient(); // should this be public? oh well!

    public Bot() throws LoginException, InterruptedException {
        Settings settings = SettingsManager.getInstance().getSettings();
        jda = new JDABuilder(settings.getDiscordToken()).build();
        jda.awaitReady();
        jda.addEventListener(new DiscordMessageEvent());
        WebServer ws = new WebServer();

        ws.start();
        ws.setName("WebServer");

        // TODO: Fix the race condition here. If a message is sent and it hasn't gotten the mentionables, it will NPE.
        Timer mentionablesTimer = new Timer();
        mentionablesTimer.schedule(new MentionablesTimerTask(), 0L, TimeUnit.HOURS.toMillis(1));

        Timer pingMeasurementTimer = new Timer();
        pingMeasurementTimer.schedule(new ActivityTimerTask(), 0L, TimeUnit.MINUTES.toMillis(1));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> LoggerFactory.getLogger(getClass()).info("Bot is now shutting down.")));
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
}
