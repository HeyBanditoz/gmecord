package io.banditoz.gmecord.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Member {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("muted")
    @Expose
    private Boolean muted;
    @SerializedName("autokicked")
    @Expose
    private Boolean autokicked;
    @SerializedName("roles")
    @Expose
    private List<String> roles = null;
    @SerializedName("name")
    @Expose
    private String name;

    @Override
    public String toString() {
        return "Member{" +
                "userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", id='" + id + '\'' +
                ", muted=" + muted +
                ", autokicked=" + autokicked +
                ", roles=" + roles +
                ", name='" + name + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getMuted() {
        return muted;
    }

    public void setMuted(Boolean muted) {
        this.muted = muted;
    }

    public Boolean getAutokicked() {
        return autokicked;
    }

    public void setAutokicked(Boolean autokicked) {
        this.autokicked = autokicked;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}