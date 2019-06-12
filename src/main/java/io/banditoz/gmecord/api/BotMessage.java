package io.banditoz.gmecord.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class BotMessage {

    @SerializedName("bot_id")
    @Expose
    private String botId;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("attachments")
    @Expose
    private List<Attachment> attachments;

    @Override
    public String toString() {
        return "BotMessage{" +
                "botId='" + botId + '\'' +
                ", text='" + text + '\'' +
                ", attachments=" + attachments +
                '}';
    }

    public BotMessage(String botId, String text) {
        this.botId = botId;
        this.text = text;
    }

    public BotMessage(String botId, String text, List<Attachment> initialAttachments) {
        this.botId = botId;
        this.text = text;
        this.attachments = initialAttachments;
    }

    public BotMessage() {
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

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}