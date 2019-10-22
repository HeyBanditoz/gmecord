package io.banditoz.gmecord;

import io.banditoz.gmecord.api.BotMessage;
import io.banditoz.gmecord.util.SerializerDeserializer;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GroupmeMessageSender {
    private final BotMessage message;
    private final Settings settings;
    private final Logger logger;
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    /**
     * Constructor with a BotMessage.
     * @param message The BotMessage to send,
     */
    public GroupmeMessageSender(BotMessage message) {
        logger = LoggerFactory.getLogger(GroupmeMessageSender.class);
        settings = SettingsManager.getInstance().getSettings();
        this.message = message;
    }

    /**
     * Sends a message to GroupMe, building the attachment list and message.
     */
    public void sendMessageToGroupMe() {
        String json;
        String botID = settings.getBotID();
        message.setBotId(botID);
        json = SerializerDeserializer.serializeMessage(message);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("https://api.groupme.com/v3/bots/post")
                .post(body)
                .build();
        logger.debug("Sending message to Groupme: " + json);
        try (Response response = Bot.client.newCall(request).execute()) {
            logger.debug(response.protocol() + " " + response.code() + " " + response.message() + " (Took " + (response.receivedResponseAtMillis() - response.sentRequestAtMillis()) + " ms for a response.)");
            logger.debug("Their response headers:\n" + response.headers());
        } catch (Exception e) {
            logger.error("Error on sending message to Groupme! json: " + json + " ", e);
        }
    }
}
