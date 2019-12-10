package io.banditoz.gmecord.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Group {
    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", groupId='" + groupId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", creatorUserId='" + creatorUserId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", officeMode=" + officeMode +
                ", shareUrl='" + shareUrl + '\'' +
                ", shareQrCodeUrl='" + shareQrCodeUrl + '\'' +
                ", members=" + members +
                ", messages=" + messages +
                ", maxMembers=" + maxMembers +
                '}';
    }

    @JsonProperty("id")
    private String id;
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("type")
    private String type;
    @JsonProperty("description")
    private String description;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("creator_user_id")
    private String creatorUserId;
    @JsonProperty("created_at")
    private int createdAt;
    @JsonProperty("updated_at")
    private int updatedAt;
    @JsonProperty("office_mode")
    private boolean officeMode;
    @JsonProperty("share_url")
    private String shareUrl;
    @JsonProperty("share_qr_code_url")
    private String shareQrCodeUrl;
    @JsonProperty("members")
    private List<Member> members = null;
    @JsonProperty("messages")
    private Messages messages;
    @JsonProperty("max_members")
    private Integer maxMembers;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("group_id")
    public String getGroupId() {
        return groupId;
    }

    @JsonProperty("group_id")
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("phone_number")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("image_url")
    public Object getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("creator_user_id")
    public String getCreatorUserId() {
        return creatorUserId;
    }

    @JsonProperty("creator_user_id")
    public void setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    @JsonProperty("created_at")
    public int getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public int getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(int updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("office_mode")
    public boolean getOfficeMode() {
        return officeMode;
    }

    @JsonProperty("office_mode")
    public void setOfficeMode(boolean officeMode) {
        this.officeMode = officeMode;
    }

    @JsonProperty("share_url")
    public Object getShareUrl() {
        return shareUrl;
    }

    @JsonProperty("share_url")
    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    @JsonProperty("share_qr_code_url")
    public Object getShareQrCodeUrl() {
        return shareQrCodeUrl;
    }

    @JsonProperty("share_qr_code_url")
    public void setShareQrCodeUrl(String shareQrCodeUrl) {
        this.shareQrCodeUrl = shareQrCodeUrl;
    }

    @JsonProperty("members")
    public List<Member> getMembers() {
        return members;
    }

    @JsonProperty("members")
    public void setMembers(List<Member> members) {
        this.members = members;
    }

    @JsonProperty("messages")
    public Object getMessages() {
        return messages;
    }

    @JsonProperty("messages")
    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    @JsonProperty("max_members")
    public Integer getMaxMembers() {
        return maxMembers;
    }

    @JsonProperty("max_members")
    public void setMaxMembers(Integer maxMembers) {
        this.maxMembers = maxMembers;
    }
}