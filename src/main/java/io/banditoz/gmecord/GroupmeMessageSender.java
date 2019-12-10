package io.banditoz.gmecord;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.banditoz.gmecord.api.BotMessage;
import io.banditoz.gmecord.util.SerializerDeserializer;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupmeMessageSender {
    private static final Settings settings = SettingsManager.getInstance().getSettings();
    private static final Logger logger = LoggerFactory.getLogger(GroupmeMessageSender.class);
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

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
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("https://api.groupme.com/v3/bots/post")
                .post(body)
                .build();
        logger.debug("Sending message to Groupme: " + json);
        try (Response response = Bot.getOkHttpClient().newCall(request).execute()) {
            logger.debug(response.protocol() + " " + response.code() + " " + response.message() + " (Took " + (response.receivedResponseAtMillis() - response.sentRequestAtMillis()) + " ms for a response.)");
            logger.debug("Their response headers:\n" + response.headers());
        } catch (Exception e) {
            logger.error("Error on sending message to Groupme! json: " + json + " ", e);
        }
    }
}
