package io.banditoz.gmecord.events;

import io.banditoz.gmecord.*;
import io.banditoz.gmecord.api.Attachment;
import io.banditoz.gmecord.util.BuildAttachments;
import io.banditoz.gmecord.util.UploadToPtpbInstance;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.ServiceUnavailableException;
import java.io.IOException;
import java.util.ArrayList;

public class DiscordMessageEvent extends ListenerAdapter {
    private Logger logger;
    public DiscordMessageEvent() {
        logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        // I'm honestly surprised this method works...
        String message = e.getMessage().getContentDisplay();
        ArrayList<Attachment> attachments = new ArrayList<>();
        if (e.getChannel().getId().equals(SettingsManager.getInstance().getSettings().getChannel()) &&
                (e.getAuthor().getId().compareToIgnoreCase(Bot.getJda().getSelfUser().getId()) != 0)) {
            if (!e.getMessage().getAttachments().isEmpty()) {
                try {
                    attachments = BuildAttachments.buildImageAttachments(e);
                    if (attachments.size() > 1) {
                        DiscordMessageCreator creator = new DiscordMessageCreator("<@" + e.getAuthor().getId() + ">, due to " +
                                "a bug in Groupme, only your first image was sent.", true);
                        creator.build();
                        new DiscordMessageSender(creator.createMessage()).sendMessageToDiscord();
                    }
                    message += BuildAttachments.buildOtherAttachments(e).toString();
                } catch (Exception ex) {
                    logger.error("Exception on building attachments!", ex);
                    DiscordMessageCreator creator = new DiscordMessageCreator("<@" + e.getAuthor().getId() + ">," +
                            "there was an error while building the attachments." +
                            " Exception: `" + ex + "`", true);
                    creator.build();
                    new DiscordMessageSender(creator.createMessage()).sendMessageToDiscord();
                }
            }
            if (message.length() > 965) {
                messageIsTooLong(e, message);
            }
            else {
                GroupmeMessageCreator gmeMessage = new GroupmeMessageCreator("<" + e.getAuthor().getName() + "> " + message, false, attachments);
                gmeMessage.build();
                new GroupmeMessageSender(gmeMessage.getMessage()).sendMessageToGroupMe();
            }
        }
    }

    private void messageIsTooLong(MessageReceivedEvent e, String message) {
        if (SettingsManager.getInstance().getSettings().getPtpbEndpoint() != null) {
            String url;
            GroupmeMessageCreator gmeMessage = new GroupmeMessageCreator("<" + e.getAuthor().getName() + "> " + message, false);
            gmeMessage.build();
            try {
                url = UploadToPtpbInstance.uploadToPtpb(gmeMessage.getMessage().getText());
                GroupmeMessageCreator sysMessage = new GroupmeMessageCreator("Message from user " + e.getAuthor().getName() + " is too long! Pastebin link: " + url, true);
                sysMessage.build();
                new GroupmeMessageSender(sysMessage.getMessage()).sendMessageToGroupMe();
            } catch (IOException | ServiceUnavailableException ex) {
                logger.error("Exception thrown! Letting Discord know their message will not be delivered...", ex);
                DiscordMessageCreator creator = new DiscordMessageCreator("<@" + e.getAuthor().getId() + ">, your message " +
                        "was greater than 965 characters, and the configured ptpb instance is not working! Therefore, your message" +
                        " will not be delivered over the bridge. Exception: `" + ex + "`", true);
                creator.build();
                new DiscordMessageSender(creator.createMessage()).sendMessageToDiscord();
            }
        }
        else {
            DiscordMessageCreator creator = new DiscordMessageCreator("<@" + e.getAuthor().getId() + ">, " +
                    "your message was greater than 965 characters, and the ptpb endpoint" +
                    "is not configured. Therefore, your message will not be delivered" +
                    "over the bridge.", true);
            creator.build();
            new DiscordMessageSender(creator.createMessage()).sendMessageToDiscord();
        }
    }
}
