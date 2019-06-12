package io.banditoz.gmecord.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    @SerializedName("attachments")
    @Expose
    private Attachment[] attachments = null;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("created_at")
    @Expose
    private int createdAt;
    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sender_id")
    @Expose
    private String senderId;
    @SerializedName("sender_type")
    @Expose
    private String senderType;
    @SerializedName("source_guid")
    @Expose
    private String sourceGuid;
    @SerializedName("system")
    @Expose
    private boolean system;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public Attachment[] getAttachments() {
        return attachments;
    }

    public void setAttachments(Attachment[] attachments) {
        this.attachments = attachments;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    public String getSourceGuid() {
        return sourceGuid;
    }

    public void setSourceGuid(String sourceGuid) {
        this.sourceGuid = sourceGuid;
    }

    public boolean getSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
