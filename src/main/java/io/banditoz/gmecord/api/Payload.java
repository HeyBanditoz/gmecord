package io.banditoz.gmecord.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payload {
    @JsonProperty("payload")
    private ImagePayload imagePayload;

    public String getUrl() {
        return imagePayload.getUrl();
    }

    public void setUrl(String url) {
        imagePayload.setUrl(url);
    }

    public String getPictureUrl() {
        return imagePayload.getPictureUrl();
    }

    public void setPictureUrl(String url) {
        imagePayload.setPictureUrl(url);
    }
}

class ImagePayload {
    @JsonProperty("url")
    private String url;
    @JsonProperty("picture_url")
    private String pictureUrl;

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("picture_url")
    public String getPictureUrl() {
        return pictureUrl;
    }

    @JsonProperty("picture_url")
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}