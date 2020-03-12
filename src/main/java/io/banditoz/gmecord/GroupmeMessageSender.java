package io.banditoz.gmecord;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.banditoz.gmecord.api.BotMessage;
import io.banditoz.gmecord.util.SerializerDeserializer;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupmeMessageSender {
    private static final Settings settings = SettingsManager.getInstance().getSettings();
    private static final Logger logger = LoggerFactory.getLogger(GroupmeMessageSender.class);
    private static final MediaType MEDIA_TYPE_JSON = MediaType.get("application/json; charset=utf-8");

    /**
     * Sends a message to GroupMe, building the attachment list and message.
     */
    public static void sendMessageToGroupMe(BotMessage message) {
        String json;
        String botID = settings.getBotID();
        message.setBotId(botID);
        try {
            json = SerializerDeserializer.serializeMessage(message);
        } catch (JsonProcessingException e) {
            logger.error("Error while serializing message!", e);
            return; // sorry!
        }
        RequestBody body = RequestBody.create(json, MEDIA_TYPE_JSON);
        Request request = new Request.Builder()
                .url("https://api.groupme.com/v3/bots/post")
                .post(body)
                .build();
        try {
            Response response = Bot.getOkHttpClient().newCall(request).execute();
            if (response.code() > 400) {
                logger.warn("Groupme returned " + response.code() + " while sending a message.");
                DiscordMessageCreator creator = new DiscordMessageCreator("Message `" + truncate(message.getText(), 50) + "` may have failed to send: Groupme returned " + response.code() + ".", true);
                DiscordMessageSender.sendMessageToDiscord(creator.getMessage());
            }
        } catch (Exception e) {
            logger.error("Error on sending message to Groupme! json: " + json + " ", e);
            DiscordMessageCreator creator = new DiscordMessageCreator("Message `" + truncate(message.getText(), 50) + "` may have failed to send, exception: `" + e.toString() + "`", true);
            DiscordMessageSender.sendMessageToDiscord(creator.getMessage());

        }
    }

    private static String truncate(String string, int length) {
        if (string.length() > length) {
            return string.substring(0, length) + "...";
        }
        else {
            return string;
        }
    }
}
