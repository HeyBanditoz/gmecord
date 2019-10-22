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

    public void run() {
        Logger logger = LoggerFactory.getLogger(WebServer.class);
        Settings settings = SettingsManager.getInstance().getSettings();
        Javalin webServer = Javalin.create().start(settings.getPort());

        if (settings.isWebAuthenticationEnabled()) {
            logger.info("Authentication is enabled.");
            webServer.before("/msg/*", ctx -> {
                username = settings.getUsername();
                password = settings.getPassword();
            });
        }

        webServer.post("/msg/", ctx -> {
            if (settings.isWebAuthenticationEnabled()) {
                try {
                    if (authenticate(ctx.basicAuthCredentials())) {
                        MessageHandler.handle(ctx);
                    } else {
                        ctx.status(Response.SC_UNAUTHORIZED);
                        ctx.result("401 Unauthorized\n");
                    }
                } catch (IllegalArgumentException ex) {
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
        return creds.getUsername().equals(username) && creds.getPassword().equals(password);
    }
}
