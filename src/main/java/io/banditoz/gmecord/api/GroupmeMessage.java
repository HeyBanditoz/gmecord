package io.banditoz.gmecord.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Represents a GroupMe message.
 *
 */
public class GroupmeMessage {

    @Override
    public String toString() {
        return "GroupmeMessage{" +
                "attachments=" + Arrays.toString(attachments) +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", createdAt=" + createdAt +
                ", groupId='" + groupId + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", senderId='" + senderId + '\'' +
                ", senderType='" + senderType + '\'' +
                ", sourceGuid='" + sourceGuid + '\'' +
                ", system=" + system +
                ", text='" + text + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    @JsonProperty("attachments")
    private Attachment[] attachments = null;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("created_at")
    private int createdAt;
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("sender_id")
    private String senderId;
    @JsonProperty("sender_type")
    private String senderType;
    @JsonProperty("source_guid")
    private String sourceGuid;
    @JsonProperty("system")
    private boolean system;
    @JsonProperty("text")
    private String text;
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("attachments")
    public Attachment[] getAttachments() {
        return attachments;
    }

    @JsonProperty("attachments")
    public void setAttachments(Attachment[] attachments) {
        this.attachments = attachments;
    }

    @JsonProperty("avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @JsonProperty("avatar_url")
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonProperty("created_at")
    public int getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("group_id")
    public String getGroupId() {
        return groupId;
    }

    @JsonProperty("group_id")
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("sender_id")
    public String getSenderId() {
        return senderId;
    }

    @JsonProperty("sender_id")
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @JsonProperty("sender_type")
    public String getSenderType() {
        return senderType;
    }

    @JsonProperty("sender_type")
    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    @JsonProperty("source_guid")
    public String getSourceGuid() {
        return sourceGuid;
    }

    @JsonProperty("source_guid")
    public void setSourceGuid(String sourceGuid) {
        this.sourceGuid = sourceGuid;
    }

    @JsonProperty("system")
    public boolean getSystem() {
        return system;
    }

    @JsonProperty("system")
    public void setSystem(boolean system) {
        this.system = system;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
