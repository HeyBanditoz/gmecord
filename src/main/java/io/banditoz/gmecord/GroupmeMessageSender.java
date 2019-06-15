package io.banditoz.gmecord;

import com.google.gson.Gson;
import io.banditoz.gmecord.api.BotMessage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

public class GroupmeMessageSender {
    private final BotMessage message;
    private final Settings settings;
    private final Logger logger;

    /**
     * Constructor with a BotMessage.
     * @param message The BotMessage to send,
     */
    public GroupmeMessageSender(BotMessage message) {
        logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
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
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://api.groupme.com/v3/bots/post");
        json = new Gson().toJson(message);
        HttpEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        post.setEntity(entity);
        CloseableHttpResponse response;
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Sending message to Groupme: " + json);
            }
            response = client.execute(post);
            if (logger.isDebugEnabled()) {
                logger.debug(response.getStatusLine().toString());
                logger.debug("Their response headers: " + Arrays.toString(response.getAllHeaders()));
            }
        } catch (IOException e) {
            logger.error("Error on sending message to Groupme! json:" + json + " ", e);
        } finally {
            post.reset();
        }
    }
}
