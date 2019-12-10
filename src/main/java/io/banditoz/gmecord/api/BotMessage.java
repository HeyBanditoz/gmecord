package io.banditoz.gmecord.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class BotMessage {
    @JsonProperty("bot_id")
    private String botId;
    @JsonProperty("text")
    private String text;
    @JsonProperty("attachments")
    private List<Attachment> attachments;

    @Override
    public String toString() {
        return "BotMessage{" +
                "botId='" + botId + '\'' +
                ", text='" + text + '\'' +
                ", attachments=" + attachments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotMessage that = (BotMessage) o;
        return Objects.equals(botId, that.botId) &&
                Objects.equals(text, that.text) &&
                Objects.equals(attachments, that.attachments);
    }

    @JsonProperty("bot_id")
    public String getBotId() {
        return botId;
    }

    @JsonProperty("bot_id")
    public void setBotId(String botId) {
        this.botId = botId;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("attachments")
    public List<Attachment> getAttachments() {
        return attachments;
    }

    @JsonProperty("attachments")
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}