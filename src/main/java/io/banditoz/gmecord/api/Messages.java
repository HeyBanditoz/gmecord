package io.banditoz.gmecord.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Messages {

    @JsonProperty("count")
    private Integer count;
    @JsonProperty("last_message_id")
    private String lastMessageId;
    @JsonProperty("last_message_created_at")
    private Integer lastMessageCreatedAt;
    @JsonProperty("preview")
    private MessagesPreview messagesPreview;

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("last_message_id")
    public String getLastMessageId() {
        return lastMessageId;
    }

    @JsonProperty("last_message_id")
    public void setLastMessageId(String lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    @JsonProperty("last_message_created_at")
    public Integer getLastMessageCreatedAt() {
        return lastMessageCreatedAt;
    }

    @JsonProperty("last_message_created_at")
    public void setLastMessageCreatedAt(Integer lastMessageCreatedAt) {
        this.lastMessageCreatedAt = lastMessageCreatedAt;
    }

    @JsonProperty("preview")
    public MessagesPreview getMessagesPreview() {
        return messagesPreview;
    }

    @JsonProperty("preview")
    public void setMessagesPreview(MessagesPreview messagesPreview) {
        this.messagesPreview = messagesPreview;
    }
}