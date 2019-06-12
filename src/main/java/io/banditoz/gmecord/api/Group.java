package io.banditoz.gmecord.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("creator_user_id")
    @Expose
    private String creatorUserId;
    @SerializedName("created_at")
    @Expose
    private int createdAt;
    @SerializedName("updated_at")
    @Expose
    private int updatedAt;
    @SerializedName("office_mode")
    @Expose
    private boolean officeMode;
    @SerializedName("share_url")
    @Expose
    private String shareUrl;
    @SerializedName("share_qr_code_url")
    @Expose
    private String shareQrCodeUrl;
    @SerializedName("members")
    @Expose
    private List<Member> members = null;
    @SerializedName("messages")
    @Expose
    private Messages messages;
    @SerializedName("max_members")
    @Expose
    private Integer maxMembers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public int getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(int updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getOfficeMode() {
        return officeMode;
    }

    public void setOfficeMode(boolean officeMode) {
        this.officeMode = officeMode;
    }

    public Object getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Object getShareQrCodeUrl() {
        return shareQrCodeUrl;
    }

    public void setShareQrCodeUrl(String shareQrCodeUrl) {
        this.shareQrCodeUrl = shareQrCodeUrl;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Object getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public Integer getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(Integer maxMembers) {
        this.maxMembers = maxMembers;
    }

}