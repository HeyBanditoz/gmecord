package io.banditoz.gmecord;

import io.banditoz.gmecord.api.Attachment;
import io.banditoz.gmecord.api.GroupmeMessage;
import io.banditoz.gmecord.util.MarkdownUtils;

import java.util.HashMap;

public class DiscordMessageCreator {
    private StringBuilder finalizedMessage;
    private GroupmeMessage message;
    private String formattedName;

    public DiscordMessageCreator(GroupmeMessage msg, boolean isSystemMessage) {
        this.message = msg;
        if (isSystemMessage) {
            formattedName = "<SYSTEM MESSAGE> <" + msg.getName() + ">";
        }
        else {
            formattedName = "<" + msg.getName() + ">";
        }
        // Escape Discord's markdown so the message can't get messed up unintentionally
        formattedName = MarkdownUtils.escapeMarkdownCharacters(formattedName);
        formattedName = formattedName.replaceAll("https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&//=]*)", "[URL Sanitized]");
        if (formattedName.contains("SYSTEM MESSAGE") && !isSystemMessage) {
            formattedName = "<Sanitized>";
        }

        // Then bold their name so it looks nicer
        formattedName = "**" + formattedName + "** ";

        build();
    }

    /**
     * Alternate constructor that doesn't build attachments, checks for mentions, etc.
     * @param msg The String to send.
     * @param isSystemMessage If we want to append SYSTEM MESSAGE to the beginning of the message.
     */
    public DiscordMessageCreator(String msg, boolean isSystemMessage) {
        if (isSystemMessage) {
            finalizedMessage = new StringBuilder("**<SYSTEM MESSAGE>** ");
            finalizedMessage.append(msg);
        }
        else {
            finalizedMessage = new StringBuilder(msg);
        }
    }

    /**
     * Gets the formatted message.
     * @return The formatted message.
     */
    public String getMessage() {
        return finalizedMessage.toString();
    }

    /**
     * Builds the formatted message.
     */
    private void build() {
        if (message != null) {
            finalizedMessage = new StringBuilder();
            if (message.getAttachments() != null && message.getAttachments().length != 0) {
                buildAttachments();
            }
            finalizedMessage.append(message.getText());
            checkForMentionables();
            finalizedMessage.insert(0, formattedName); // insert formatted name at beginning of string last so the name can't contain a mention
        }
    }

    private void checkForMentionables() {
        String replacement = finalizedMessage.toString();
        boolean found = false;
        HashMap<String, String> mentionables = Bot.getMentionableDiscord();
        for (String k : mentionables.keySet()) {
            if (replacement.contains("@" + k)) {
                found = true;
                replacement = replacement.replace("@" + k, "<@" + mentionables.get(k) + ">");
            }
        }
        if (found) finalizedMessage = new StringBuilder(replacement);
    }

    /**
     * Build Groupme attachments to a string.
     */
    private void buildAttachments() {
        for (Attachment a : message.getAttachments()) {
            if (a.getType().compareToIgnoreCase("image") == 0) {
                finalizedMessage.append("<IMAGE> ")
                        .append("URL: ")
                        .append(a.getUrl())
                        .append(" ");
            }
            else if (a.getType().compareToIgnoreCase("location") == 0) {
                String googleMaps = "https://maps.google.com/maps?q=";
                finalizedMessage.append("<LOCATION>")
                        .append(" Latitude: " )
                        .append(a.getLat())
                        .append(", Longitude: ")
                        .append(a.getLng())
                        .append(", Name: ")
                        .append(a.getName())
                        .append(", Google Maps URL: ")
                        .append(googleMaps)
                        .append(a.getLat())
                        .append(",")
                        .append(a.getLng())
                        .append(" ");
            }
            else if (a.getType().equals("emoji")) {
                return; // emojis aren't supported.
            }
            else if (a.getType().equals("mentions")) {
                return; // mentions don't do anything on discord's side, we also don't care.
            }
            else if (a.getType().equals("postprocessing")) {
                return; // do nothing, it's just an image
            }
            else {
                finalizedMessage.append("<ATTACHMENT> Unknown attachment type: ")
                        .append(a.getType())
                        .append(", Array length: ")
                        .append(message.getAttachments().length)
                        .append(", getAttachments[0].toString() `")
                        .append(a.toString())
                        .append("` Message text: ");
            }
        }
        }
}
