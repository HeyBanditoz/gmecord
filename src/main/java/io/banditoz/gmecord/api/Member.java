package io.banditoz.gmecord.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Member {

    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("id")
    private String id;
    @JsonProperty("muted")
    private Boolean muted;
    @JsonProperty("autokicked")
    private Boolean autokicked;
    @JsonProperty("roles")
    private List<String> roles = null;
    @JsonProperty("name")
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

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    @JsonProperty("nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("muted")
    public Boolean getMuted() {
        return muted;
    }

    @JsonProperty("muted")
    public void setMuted(Boolean muted) {
        this.muted = muted;
    }

    @JsonProperty("autokicked")
    public Boolean getAutokicked() {
        return autokicked;
    }

    @JsonProperty("autokicked")
    public void setAutokicked(Boolean autokicked) {
        this.autokicked = autokicked;
    }

    @JsonProperty("roles")
    public List<String> getRoles() {
        return roles;
    }

    @JsonProperty("roles")
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
}