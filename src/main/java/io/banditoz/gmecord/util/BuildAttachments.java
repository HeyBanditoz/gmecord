package io.banditoz.gmecord.util;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.net.URI;

public class BuildAttachments {
    /**
     * Builds attachments from Discord (in this case, image URLs)
     */
    public static StringBuilder buildImageAttachments(MessageReceivedEvent e) {
        StringBuilder attachments = new StringBuilder();
        for (Message.Attachment a : e.getMessage().getAttachments()) {
            attachments.append(" <ATTACHMENT> ");
            attachments.append(URI.create(a.getUrl()));
        }
        return attachments;
    }
}
