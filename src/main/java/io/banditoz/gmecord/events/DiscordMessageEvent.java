package io.banditoz.gmecord.events;

import io.banditoz.gmecord.*;
import io.banditoz.gmecord.util.BuildAttachments;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class DiscordMessageEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        String message = e.getMessage().getContentDisplay();
        if (e.getChannel().getId().equals(SettingsManager.getInstance().getSettings().getChannel()) &&
                (e.getAuthor().getId().compareToIgnoreCase(Bot.getJda().getSelfUser().getId()) != 0)) {
            if (!e.getMessage().getAttachments().isEmpty()) {
                StringBuilder attachments = BuildAttachments.buildImageAttachments(e);
                GroupmeMessageCreator gmeMessage = new GroupmeMessageCreator("<" + e.getAuthor().getName() + "> " + attachments.toString() + message, false);
                gmeMessage.build();
                new GroupmeMessageSender(gmeMessage.getMessage()).sendMessageToGroupMe();
                return;
            }
            // TODO: Make some pastebin service (or use one (just not pastebin.com!)) to use instead of this.
            if (message.length() > 965) {
                DiscordMessageCreator creator = new DiscordMessageCreator("<@" + e.getAuthor().getId() + ">, your message was greater than 965 characters! (contains " + message.length() + " characters.) Therefore, it was not delivered over the bridge.", true);
                creator.build();
                new DiscordMessageSender(creator.createMessage()).sendMessageToDiscord();

            }
            else {
                GroupmeMessageCreator gmeMessage = new GroupmeMessageCreator("<" + e.getAuthor().getName() + "> " + message, false);
                gmeMessage.build();
                new GroupmeMessageSender(gmeMessage.getMessage()).sendMessageToGroupMe();

            }
        }
    }
}
