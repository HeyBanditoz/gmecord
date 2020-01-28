package io.banditoz.gmecord.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.banditoz.gmecord.Settings;
import io.banditoz.gmecord.api.BotMessage;
import io.banditoz.gmecord.api.GroupmeMessage;
import io.banditoz.gmecord.api.Payload;
import io.banditoz.gmecord.api.Response;
import io.banditoz.gmecord.paste.Paste;
import io.banditoz.gmecord.paste.PasteResponse;

import java.io.BufferedReader;
import java.io.IOException;

public class SerializerDeserializer {
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public static GroupmeMessage deserializeMessageGivenString(String json) throws IOException {
        return mapper.readValue(json, GroupmeMessage.class);
    }

    public static Response deserializeResponseGivenString(String json) throws NullPointerException, IOException {
        Response response = mapper.readValue(json, Response.class);
        if (response.getResponse() == null) {
            throw new NullPointerException("The response body is null!");
        }
        return response;
    }

    public static Payload deserializeImageGivenString(String json) throws NullPointerException, IOException {
        Payload payload = mapper.readValue(json, Payload.class);
        if (payload.getPictureUrl() == null) {
            throw new NullPointerException("There is no image URL here!");
        }
        return payload;
    }

    public static PasteResponse deserializePasteResponseGivenString(String json) throws IOException, IllegalArgumentException {
        PasteResponse pr = mapper.readValue(json, PasteResponse.class);
        if (pr.getStatus().compareToIgnoreCase("success") != 0) {
            throw new IllegalArgumentException("The paste response wasn't successful! Json: " + json);
        }
        return pr;
    }

    public static String serializeMessage(BotMessage message) throws JsonProcessingException {
        return mapper.writeValueAsString(message);
    }

    public static String serializePaste(Paste paste) throws JsonProcessingException {
        return mapper.writeValueAsString(paste);
    }

    public static String serializeSettings(Settings settings) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(settings);
    }

    public static Settings deserializeSettings(BufferedReader br) throws IOException {
        return mapper.readValue(br, Settings.class);
    }
}
