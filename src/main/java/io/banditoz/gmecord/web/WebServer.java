package io.banditoz.gmecord.web;

import io.banditoz.gmecord.Settings;
import io.banditoz.gmecord.SettingsManager;
import io.javalin.Javalin;
import io.javalin.core.security.BasicAuthCredentials;
import org.eclipse.jetty.server.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class WebServer extends Thread {
    private String username;
    private String password;
    private Logger logger;

    public void run() {
        Javalin webServer = Javalin.create().start(4567);
        logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
        Settings settings = SettingsManager.getInstance().getSettings();

        if (settings.isWebAuthenticationEnabled()) {
            logger.info("Authentication is enabled.");
            webServer.before("/msg/*", ctx -> {
                username = settings.getUsername();
                password = settings.getPassword();
            });
        }

        webServer.post("/msg/", ctx -> {
            if (settings.isWebAuthenticationEnabled()) {
                if (authenticate(ctx.basicAuthCredentials())) {
                    MessageHandler.handle(ctx);
                }
                else {
                    ctx.status(Response.SC_UNAUTHORIZED);
                    ctx.result("401 Unauthorized\n");
                }
            }
            else {
                MessageHandler.handle(ctx);
            }
        });
    }

    private boolean authenticate(BasicAuthCredentials creds) {
        if (Objects.isNull(creds)) {
            return false;
        }
        return creds.getUsername().equals(username) && creds.getPassword().equals(password);
    }
}
