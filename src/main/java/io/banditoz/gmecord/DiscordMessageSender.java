package io.banditoz.gmecord;

public class DiscordMessageSender {
    private static final Settings settings = SettingsManager.getInstance().getSettings();

    /**
     * Send a message to Discord.
     */
    public static void sendMessageToDiscord(String message) {
        if (message.contains("**<" + settings.getBotName() + ">**")) {
            return; // don't send bot's own message
        }
        Bot.getJda().getTextChannelById(settings.getChannel()).sendMessage(message).queue();
    }
}
