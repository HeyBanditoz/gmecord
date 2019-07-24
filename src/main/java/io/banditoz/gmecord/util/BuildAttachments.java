package io.banditoz.gmecord.util;

import io.banditoz.gmecord.GroupmeImageUploader;
import io.banditoz.gmecord.api.Attachment;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class BuildAttachments {
    /**
     * Builds attachments from Discord (in this case, image URLs)
     */
    public static ArrayList<Attachment> buildImageAttachments(MessageReceivedEvent e) throws IOException {
        ArrayList<Attachment> attachments = new ArrayList<>();
        GroupmeImageUploader giu = new GroupmeImageUploader();
        for (Message.Attachment a : e.getMessage().getAttachments()) {
            if (a.isImage() && (a.getUrl().endsWith(".png") || a.getUrl().endsWith(".jpg") || a.getUrl().endsWith(".jpeg"))) {
                Attachment attachment = new Attachment();
                attachment.setType("image");
                attachment.setUrl(giu.uploadImage(a.getUrl()));
                attachments.add(attachment);
            }
        }
        return attachments;
    }

    public static StringBuilder buildOtherAttachments(MessageReceivedEvent e) {
        StringBuilder attachments = new StringBuilder();
        for (Message.Attachment a : e.getMessage().getAttachments()) {
            if (!a.isImage())
            attachments.append(" <ATTACHMENT> ").append(URI.create(a.getUrl()));
        }
        return attachments;
    }
}
