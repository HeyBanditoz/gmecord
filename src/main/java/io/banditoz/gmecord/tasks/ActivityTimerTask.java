package io.banditoz.gmecord.tasks;

import io.banditoz.gmecord.Bot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;

import java.util.TimerTask;

public class ActivityTimerTask extends TimerTask {
    @Override
    public void run() {
        JDA jda = Bot.getJda();
        long responseTime = jda.getGatewayPing();
        Runtime runtime = Runtime.getRuntime();
        long memUsed = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
        long memTotal = runtime.maxMemory() / (1024 * 1024);
        Activity activity = Activity.playing(responseTime + " ms - " + memUsed + " MB / " + memTotal + " MB");
        jda.getPresence().setActivity(activity);
    }
}
