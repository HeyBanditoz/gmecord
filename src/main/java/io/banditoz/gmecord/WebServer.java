package io.banditoz.gmecord;

import com.qmetric.spark.authentication.AuthenticationDetails;
import com.qmetric.spark.authentication.BasicAuthenticationFilter;
import io.banditoz.gmecord.api.GroupmeMessage;
import io.banditoz.gmecord.util.SerializerDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;
public class WebServer extends Thread {
    public void run() {
        Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
        if (SettingsManager.getInstance().getSettings().isWebAuthenticationEnabled()) {
            logger.info("Authentication is enabled.");
            Spark.before(
                    new BasicAuthenticationFilter("/msg/*", new AuthenticationDetails(
                            SettingsManager.getInstance().getSettings().getUsername(),
                            SettingsManager.getInstance().getSettings().getPassword()
                    ))
            );
        }
        Spark.post("/msg/", (req, res) -> {
            try {
                res.type("application/json");
                GroupmeMessage message = SerializerDeserializer.deserializeMessageGivenString(req.body());
                if (logger.isDebugEnabled())
                    logger.debug("Message inbound from Groupme: " + message + " Raw body: " + req.body());

                DiscordMessageCreator creator = new DiscordMessageCreator(message, message.getSystem());
                creator.build();
                new DiscordMessageSender(creator.createMessage()).sendMessageToDiscord();
            } catch (Exception e) {
                DiscordMessageCreator creator = new DiscordMessageCreator("Exception thrown on Spark post! `" + e.toString() + "`", true);
                creator.build();
                new DiscordMessageSender(creator.createMessage()).sendMessageToDiscord();
                logger.error("Exception on message post! Discord notified. ", e);
            }
            return "https://upload.wikimedia.org/wikipedia/commons/thumb/0/06/Pac_Man.svg/800px-Pac_Man.svg.png";
        });
    }
}
