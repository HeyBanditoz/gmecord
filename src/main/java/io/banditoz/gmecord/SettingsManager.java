package io.banditoz.gmecord;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

// Thanks https://github.com/DV8FromTheWorld/Yui/blob/e8da929a8f637591e4da53599c39c8161be38746/src/net/dv8tion//SettingsManager.java
public class SettingsManager {
    private static SettingsManager instance;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Settings Settings;
    private final Path configFile = new File(".").toPath().resolve("Config.json");

    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    public SettingsManager() {
        Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
        if (!configFile.toFile().exists()) {
            logger.info("Creating default settings.");
            logger.info("You will need to edit the Config.json with your login information.");
            this.Settings = getDefaultSettings();
            saveSettings();
            System.exit(1);
        }
        loadSettings();
    }

    public void loadSettings() {
        try {
            BufferedReader reader = Files.newBufferedReader(configFile, StandardCharsets.UTF_8);
            this.Settings = gson.fromJson(reader, Settings.class);
            reader.close();
            LoggerFactory.getLogger(this.getClass().getCanonicalName()).info("Settings loaded.");
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass().getCanonicalName()).error("Error loading settings.", e);
        }
    }

    public Settings getSettings() {
        return Settings;
    }

    public void saveSettings() {
        String jsonOut = gson.toJson(this.Settings);
        try {
            BufferedWriter writer = Files.newBufferedWriter(configFile, StandardCharsets.UTF_8);
            writer.append(jsonOut);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        return defaultSettings;
    }
}
