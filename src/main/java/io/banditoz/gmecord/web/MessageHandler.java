package io.banditoz.gmecord.web;

import io.banditoz.gmecord.DiscordMessageCreator;
import io.banditoz.gmecord.DiscordMessageSender;
import io.banditoz.gmecord.SettingsManager;
import io.banditoz.gmecord.api.GroupmeMessage;
import io.banditoz.gmecord.util.SerializerDeserializer;
import io.javalin.http.Context;
import org.eclipse.jetty.server.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageHandler {
    public static String lastUser;
    public static void handle(Context ctx) {
        Logger logger = LoggerFactory.getLogger(MessageHandler.class);
        try {
            ctx.contentType("text/plain");
            GroupmeMessage message = SerializerDeserializer.deserializeMessageGivenString(ctx.body());
            if (message.getName() == null || message.getText() == null) {
                logger.warn("Bad request, message body: " + ctx.body());
                ctx.status(Response.SC_BAD_REQUEST);
                ctx.result("400 Bad Request\n");
                return;
            }
            logger.debug("Message inbound... Message: " +  message + " Raw body: " + ctx.body());
            if (!message.getName().equals(SettingsManager.getInstance().getSettings().getBotName())) {
                lastUser = message.getName();
                logger.debug("Setting last user to " + lastUser);
            }
            DiscordMessageCreator creator = new DiscordMessageCreator(message, message.getSystem());
            creator.build();
            new DiscordMessageSender(creator.createMessage()).sendMessageToDiscord();
        } catch (Exception e) {
            DiscordMessageCreator creator = new DiscordMessageCreator("Exception thrown on Javalin post! `" + e.toString() + "`", true);
            creator.build();
            new DiscordMessageSender(creator.createMessage()).sendMessageToDiscord();
            logger.error("Exception on message post! Discord notified. ", e);
            ctx.status(Response.SC_INTERNAL_SERVER_ERROR);
            ctx.result("500 Internal Server Error\n");
            return;
        }
        ctx.status(Response.SC_OK);
        ctx.result("https://upload.wikimedia.org/wikipedia/commons/thumb/0/06/Pac_Man.svg/800px-Pac_Man.svg.png\n");
    }
}
