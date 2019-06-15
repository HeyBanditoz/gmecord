package io.banditoz.gmecord;

public class DiscordMessageSender {
    private final StringBuilder message;
    private final Settings settings;

    public DiscordMessageSender(String message) {
        this.message = new StringBuilder(message);
        this.settings = SettingsManager.getInstance().getSettings();
    }

    /**
     * Send a message to Discord.
     */
    public void sendMessageToDiscord() {
        if (message.toString().contains("**<" + settings.getBotName() + ">**")) {
            return; // don't send bot's own message
        }
        Bot.getJda().getTextChannelById(settings.getChannel()).sendMessage(message.toString()).queue();
    }
}
