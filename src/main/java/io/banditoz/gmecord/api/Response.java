package io.banditoz.gmecord.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    @JsonProperty("response")
    private Group response;
    @JsonProperty("meta")
    private Object meta;

    @Override
    public String toString() {
        return "Response{" +
                "response=" + response +
                ", meta=" + meta +
                '}';
    }

    @JsonProperty("response")
    public Group getResponse() {
        return response;
    }

    @JsonProperty("response")
    public void setResponse(Group response) {
        this.response = response;
    }

    @JsonProperty("meta")
    public Object getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Object meta) {
        this.meta = meta;
    }
}