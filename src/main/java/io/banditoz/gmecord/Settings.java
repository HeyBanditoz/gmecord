package io.banditoz.gmecord;

public class Settings {
    private String discordToken;
    private String groupMeToken;
    private String botID;
    private String channel;
    private boolean webAuthenticationEnabled;
    private String username;
    private String password;
    private String botName;
    private String groupID;
    private String ptpbEndpoint;

    public String getDiscordToken() {
        return discordToken;
    }

    public void setDiscordToken(String discordToken) {
        this.discordToken = discordToken;
    }

    public String getGroupMeToken() {
        return groupMeToken;
    }

    public void setGroupMeToken(String groupMeToken) {
        this.groupMeToken = groupMeToken;
    }

    public String getBotID() {
        return botID;
    }

    public void setBotID(String botID) {
        this.botID = botID;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isWebAuthenticationEnabled() {
        return webAuthenticationEnabled;
    }

    public void setWebAuthenticationEnabled(boolean webAuthenticationEnabled) {
        this.webAuthenticationEnabled = webAuthenticationEnabled;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getPtpbEndpoint() {
        return ptpbEndpoint;
    }

    public void setPtpbEndpoint(String ptpbEndpoint) {
        this.ptpbEndpoint = ptpbEndpoint;
    }
}
