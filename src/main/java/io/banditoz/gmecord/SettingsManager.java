package io.banditoz.gmecord;

import io.banditoz.gmecord.util.SerializerDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

// Thanks https://github.com/DV8FromTheWorld/Yui/blob/e8da929a8f637591e4da53599c39c8161be38746/src/net/dv8tion//SettingsManager.java
public class SettingsManager {
    private static SettingsManager instance;
    private Settings Settings;
    private final Path configFile = new File(".").toPath().resolve("Config.json");
    private final Logger logger = LoggerFactory.getLogger(SettingsManager.class);

    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    public SettingsManager() {
        if (!configFile.toFile().exists()) {
            logger.info("Creating default settings.");
            logger.info("You will need to edit Config.json with your login information.");
            this.Settings = getDefaultSettings();
            saveSettings();
            System.exit(1);
        }
        loadSettings();
    }

    public void loadSettings() {
        try {
            BufferedReader reader = Files.newBufferedReader(configFile, StandardCharsets.UTF_8);
            this.Settings = SerializerDeserializer.deserializeSettings(reader);
            reader.close();
            logger.info("Settings loaded.");
        } catch (Exception e) {
            logger.error("Error loading settings.", e);
        }
    }

    public Settings getSettings() {
        return Settings;
    }

    public void saveSettings() {
        try {
            String jsonOut = SerializerDeserializer.serializeSettings(this.Settings);
            BufferedWriter writer = Files.newBufferedWriter(configFile, StandardCharsets.UTF_8);
            writer.append(jsonOut);
            writer.close();
        } catch (Exception e) {
            logger.error("Error saving settings.", e);
        }
    }

    private Settings getDefaultSettings() {
        Settings defaultSettings = new Settings();
        defaultSettings.setDiscordToken("Discord bot token here");
        defaultSettings.setGroupMeToken("GroupMe access token here");
        defaultSettings.setBotID("Groupme Bot ID here");
        defaultSettings.setChannel("Channel to be mapped here");
        defaultSettings.setUsername("Web username here");
        defaultSettings.setPassword("Web password here");
        defaultSettings.setWebAuthenticationEnabled(true);
        defaultSettings.setBotName("Put bot username here (so it doesn't respond to its own messages.)");
        defaultSettings.setGroupID("The Groupme group ID we are in");
        defaultSettings.setPort(4567);
        return defaultSettings;
    }
}
