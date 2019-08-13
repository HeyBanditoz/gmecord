package io.banditoz.gmecord.tasks;

import io.banditoz.gmecord.Bot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;

import java.text.DecimalFormat;
import java.util.TimerTask;

public class ActivityTimerTask extends TimerTask {
    @Override
    public void run() {
        JDA jda = Bot.getJda();
        DecimalFormat df = new DecimalFormat(".#"); // round to one decimal place

        long responseTime = jda.getGatewayPing();
        Runtime runtime = Runtime.getRuntime();
        double memUsed = (runtime.totalMemory() - runtime.freeMemory()) / (1024.0 * 1024.0);
        double memTotal = runtime.maxMemory() / (1024.0 * 1024.0);
        Activity activity = Activity.playing(responseTime + " ms - " + df.format(memUsed) + " MB / " + df.format(memTotal) + " MB");
        jda.getPresence().setActivity(activity);
    }
}
