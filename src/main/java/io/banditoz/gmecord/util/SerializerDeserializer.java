package io.banditoz.gmecord.util;

import com.google.gson.Gson;
import io.banditoz.gmecord.api.GroupmeMessage;
import io.banditoz.gmecord.api.Payload;
import io.banditoz.gmecord.api.Response;

public class SerializerDeserializer {
    private static Gson gson;

    static {
        gson = new Gson();
    }

    public static GroupmeMessage deserializeMessageGivenString(String json) throws NullPointerException {
        GroupmeMessage message = gson.fromJson(json, GroupmeMessage.class);
        if (message.getName() == null || message.getText() == null) {
            throw new NullPointerException("Either GroupmeMessage.getName() or GroupmeMessage.getText() is null! This should not be a valid message!");
        }
        return message;
    }

    public static Response deserializeResponseGivenString(String json) throws NullPointerException {
        Response response = gson.fromJson(json, Response.class);
        if (response.getResponse() == null) {
            throw new NullPointerException("The response body is null!");
        }
        return response;
    }

    public static Payload deserializeImageGivenString(String json) throws NullPointerException {
        Payload payload = gson.fromJson(json, Payload.class);
        if (payload.getPictureUrl() == null) {
            throw new NullPointerException("There is no image URL here!");
        }
        return payload;
    }
}
