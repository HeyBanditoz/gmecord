package io.banditoz.gmecord.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {
    @SerializedName("payload")
    @Expose
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
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("picture_url")
    @Expose
    private String pictureUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}