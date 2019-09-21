package io.banditoz.gmecord.tasks;

import io.banditoz.gmecord.Bot;
import io.banditoz.gmecord.util.BuildMentionables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class MentionablesTimerTask extends TimerTask {
    @Override
    public void run() {
        Logger logger = LoggerFactory.getLogger(MentionablesTimerTask.class);
        try {
            long before = System.nanoTime();
            Bot.setMentionableGroupme(BuildMentionables.buildGroupmeMentionables());
            Bot.setMentionableDiscord(BuildMentionables.buildDiscordMentionables());
            logger.debug("Mentionables built." + " (Took " + (System.nanoTime() - before) / 1000000 + " ms.)");
        }
        catch (Exception ex) {
            logger.error("Exception in building mentionables!", ex);
        }
    }
}
