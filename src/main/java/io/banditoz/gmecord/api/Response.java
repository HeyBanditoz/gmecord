package io.banditoz.gmecord.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("response")
    @Expose
    private Group response;
    @SerializedName("meta")
    @Expose
    private Object meta;

    @Override
    public String toString() {
        return "Response{" +
                "response=" + response +
                ", meta=" + meta +
                '}';
    }

    public Group getResponse() {
        return response;
    }

    public void setResponse(Group response) {
        this.response = response;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

}