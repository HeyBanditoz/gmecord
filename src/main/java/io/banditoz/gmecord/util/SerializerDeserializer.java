package io.banditoz.gmecord.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.banditoz.gmecord.api.BotMessage;
import io.banditoz.gmecord.api.GroupmeMessage;
import io.banditoz.gmecord.api.Payload;
import io.banditoz.gmecord.api.Response;
import io.banditoz.gmecord.paste.Paste;
import io.banditoz.gmecord.paste.PasteResponse;

public class SerializerDeserializer {
    private static final Gson gson;

    static {
        gson = new GsonBuilder().serializeNulls().create();
    }

    public static GroupmeMessage deserializeMessageGivenString(String json) throws NullPointerException {
        return gson.fromJson(json, GroupmeMessage.class);
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

    public static PasteResponse deserializePasteResponseGivenString(String json) {
        PasteResponse pr = gson.fromJson(json, PasteResponse.class);
        if (pr.getStatus().compareToIgnoreCase("success") != 0) {
            throw new IllegalArgumentException("The paste response wasn't successful! Json: " + json);
        }
        return pr;
    }

    public static String serializeMessage(BotMessage message) {
        return gson.toJson(message);
    }

    public static String serializePaste(Paste paste) {
        return gson.toJson(paste);
    }
}
