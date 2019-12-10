package io.banditoz.gmecord.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MessagesPreview {

    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("text")
    private String text;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("attachments")
    private List<Object> attachments = null;

    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    @JsonProperty("nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("attachments")
    public List<Object> getAttachments() {
        return attachments;
    }

    @JsonProperty("attachments")
    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments;
    }
}