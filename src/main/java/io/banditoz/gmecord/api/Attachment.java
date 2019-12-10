package io.banditoz.gmecord.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Attachment {
    @Override
    public String toString() {
        return "Attachment{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", name='" + name + '\'' +
                ", userIds=" + userIds +
                ", loci=" + loci +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(url, that.url) &&
                Objects.equals(lat, that.lat) &&
                Objects.equals(lng, that.lng) &&
                Objects.equals(name, that.name) &&
                Objects.equals(userIds, that.userIds) &&
                Objects.equals(loci, that.loci);
    }

    @JsonProperty("type")
    private String type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lng")
    private String lng;
    @JsonProperty("name")
    private String name;
    @JsonProperty("user_ids")
    private List<String> userIds = null;
    @JsonProperty("loci")
    private List<List<Integer>> loci;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("lat")
    public String getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(String lat) {
        this.lat = lat;
    }

    @JsonProperty("lng")
    public String getLng() {
        return lng;
    }

    @JsonProperty("lng")
    public void setLng(String lng) {
        this.lng = lng;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("user_ids")
    public List<String> getUserIds() {
        return userIds;
    }

    @JsonProperty("user_ids")
    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    @JsonProperty("loci")
    public List<List<Integer>> getLoci() {
        return loci;
    }

    @JsonProperty("loci")
    public void setLoci(List<List<Integer>> loci) {
        this.loci = loci;
    }
}