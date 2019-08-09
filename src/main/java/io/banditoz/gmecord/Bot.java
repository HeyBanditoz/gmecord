package io.banditoz.gmecord;

import io.banditoz.gmecord.events.DiscordMessageEvent;
import io.banditoz.gmecord.util.BuildMentionables;
import io.banditoz.gmecord.web.WebServer;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Bot {
    private static JDA jda;
    private static HashMap<String, String> mentionableGroupme;
    private static HashMap<String, String> mentionableDiscord;
    public static final OkHttpClient client = new OkHttpClient(); // should this be public? oh well!

    public Bot() throws LoginException, InterruptedException {
        Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
        Settings settings = SettingsManager.getInstance().getSettings();
        jda = new JDABuilder(settings.getDiscordToken()).build();
        jda.awaitReady();
        jda.addEventListener(new DiscordMessageEvent());
        WebServer ws = new WebServer();

        ws.start();
        ws.setName("WebServer");

        // TODO: Fix the race condition here. If a message is sent and it hasn't gotten the mentionables, it will NPE.
        Timer mentionablesTimer = new Timer();
        TimerTask rebuildMentionables = new TimerTask() {
            @Override
            public void run() {
                try {
                    long before = System.nanoTime();
                    mentionableGroupme = BuildMentionables.buildGroupmeMentionables();
                    mentionableDiscord = BuildMentionables.buildDiscordMentionables();
                    logger.info("Mentionables built." + " (Took " + (System.nanoTime() - before) / 1000000 + " ms.)");
                }
                catch (Exception ex) {
                    logger.error("Exception in building mentionables!", ex);
                }
            }
        };
        mentionablesTimer.schedule(rebuildMentionables, 0L, TimeUnit.HOURS.toMillis(1));

        Timer pingMeasurementTimer = new Timer();
        TimerTask getPings = new TimerTask() {
            @Override
            public void run() {
                long responseTime = jda.getPing();
                Game game = Game.playing(responseTime + " ms.");
                jda.getPresence().setGame(game);
            }
        };
        pingMeasurementTimer.schedule(getPings, 0L, TimeUnit.MINUTES.toMillis(1));
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
