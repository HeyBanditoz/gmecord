package io.banditoz.gmecord;

import io.banditoz.gmecord.api.GroupmeMessage;

import java.util.HashMap;

public class DiscordMessageCreator {
    private StringBuilder finalizedMessage;
    private GroupmeMessage message;
    private String formattedName;

    public DiscordMessageCreator(GroupmeMessage msg, boolean isSystemMessage) {
        this.message = msg;
        if (isSystemMessage) {
            formattedName = "**<SYSTEM MESSAGE> <" + msg.getName() + ">** ";
        }
        else {
            formattedName = "**<" + msg.getName() + ">** ";
        }
    }

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
    public String createMessage() {
        return finalizedMessage.toString();
    }

    /**
     * Builds the formatted message.
     */
    public void build() {
        if (message != null) {
            finalizedMessage = new StringBuilder(formattedName);
            if (message.getAttachments() != null && message.getAttachments().length != 0) {
                buildAttachments();
            }
            finalizedMessage.append(message.getText());
            checkForMentionables();
        }
    }

    private void checkForMentionables() {
        String replacement = finalizedMessage.toString();
        boolean found = false;
        HashMap<String, String> mentionables = Bot.getMentionableDiscord();
        for (String k : mentionables.keySet()) {
            if (finalizedMessage.toString().contains("@" + k)) {
                found = true;
                replacement = replacement.replace("@" + k, "<@" + mentionables.get(k) + ">");
            }
        }
        if (found) finalizedMessage = new StringBuilder(replacement);
    }

    /**
     * Build Groupme attachments to a string.
     * This makes a bold assumption that there shall be only one attachment per message (even though it's an array?)
     * Groupme's API documentation is the worst I've ever seen (I actually haven't seen many APIs, I'm new to this.)
     * I've tested it, I can't send an image and a location.
     * Here's to hoping.
     */
    private void buildAttachments() {
        if (message.getAttachments()[0].getType().compareToIgnoreCase("image") == 0) {
            finalizedMessage.append("<IMAGE> ")
                    .append("URL: ")
                    .append(message.getAttachments()[0].getUrl())
                    .append(" ");
        }
        else if (message.getAttachments()[0].getType().compareToIgnoreCase("location") == 0) {
            String googleMaps = "http://maps.google.com/maps?q=";
            finalizedMessage.append("<LOCATION>")
                    .append(" Latitude: " )
                    .append(message.getAttachments()[0].getLat())
                    .append(", Longitude: ")
                    .append(message.getAttachments()[0].getLng())
                    .append(", Name: ")
                    .append(message.getAttachments()[0].getName())
                    .append(", Google Maps URL: ")
                    .append(googleMaps)
                    .append(message.getAttachments()[0].getLat())
                    .append(",")
                    .append(message.getAttachments()[0].getLng())
                    .append(" ");
        }
        else if (message.getAttachments()[0].getType().compareToIgnoreCase("emoji") == 0) {
            return; // emojis aren't supported.
        }
        else if (message.getAttachments()[0].getType().compareToIgnoreCase("mentions") == 0) {
            return; // mentions don't do anything on discord's side, we also don't care.
        }
        else {
            finalizedMessage.append("<ATTACHMENT> Unknown attachment type: ")
                    .append(message.getAttachments()[0].getType())
                    .append(", Array length: ")
                    .append(message.getAttachments().length)
                    .append(", getAttachments[0].toString() `")
                    .append(message.getAttachments()[0].toString())
                    .append("` Message text: ");
        }
    }
}
