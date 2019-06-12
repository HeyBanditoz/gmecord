package io.banditoz.gmecord.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessagesPreview {

    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("attachments")
    @Expose
    private List<Object> attachments = null;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Object> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments;
    }

}