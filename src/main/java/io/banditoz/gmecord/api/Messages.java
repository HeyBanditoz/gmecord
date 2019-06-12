package io.banditoz.gmecord.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Messages {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("last_message_id")
    @Expose
    private String lastMessageId;
    @SerializedName("last_message_created_at")
    @Expose
    private Integer lastMessageCreatedAt;
    @SerializedName("messagesPreview")
    @Expose
    private MessagesPreview messagesPreview;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(String lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public Integer getLastMessageCreatedAt() {
        return lastMessageCreatedAt;
    }

    public void setLastMessageCreatedAt(Integer lastMessageCreatedAt) {
        this.lastMessageCreatedAt = lastMessageCreatedAt;
    }

    public MessagesPreview getMessagesPreview() {
        return messagesPreview;
    }

    public void setMessagesPreview(MessagesPreview messagesPreview) {
        this.messagesPreview = messagesPreview;
    }

}