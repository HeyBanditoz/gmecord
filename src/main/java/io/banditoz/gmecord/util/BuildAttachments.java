package io.banditoz.gmecord.util;

import io.banditoz.gmecord.GroupmeImageUploader;
import io.banditoz.gmecord.api.Attachment;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildAttachments {
    private static final Pattern URL_PATTERN = Pattern.compile("https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&//=]*)");

    /**
     * Builds attachments from Discord (in this case, image URLs)
     */
    public static ArrayList<Attachment> buildImageAttachments(MessageReceivedEvent e) throws IOException {
        ArrayList<Attachment> attachments = new ArrayList<>();
        for (Message.Attachment a : e.getMessage().getAttachments()) {
            if (isImage(a)) {
                Attachment attachment = new Attachment();
                attachment.setType("image");
                attachment.setUrl(GroupmeImageUploader.uploadImage(a.getUrl()));
                attachments.add(attachment);
            }
        }
        Matcher m = URL_PATTERN.matcher(e.getMessage().getContentDisplay());
        if (m.find()) {
            Attachment attachment = new Attachment();
            attachment.setType("image");
            try {
                attachment.setUrl(GroupmeImageUploader.uploadImage(m.group(0)));
            } catch (IllegalArgumentException ex) {
                return attachments; // TODO Make this without exceptions, you're lazy!
            }
            attachments.add(attachment);
        }
        return attachments;
    }

    public static StringBuilder buildOtherAttachments(MessageReceivedEvent e) {
        StringBuilder attachments = new StringBuilder();
        for (Message.Attachment a : e.getMessage().getAttachments()) {
            if (!isImage(a))
            attachments.append(" <ATTACHMENT> ").append(URI.create(a.getUrl()));
        }
        return attachments;
    }

    /**
     * Checks whether or not the attachment is an image. This is hacky, but it seems to work better than
     * what JDA implements.
     * @param a The attachment to check.
     * @return true if it is an image.
     */
    private static boolean isImage(Message.Attachment a) {
        return a.getUrl().endsWith(".png") || a.getUrl().endsWith(".jpg") || a.getUrl().endsWith(".jpeg");
    }

    //private static
}
