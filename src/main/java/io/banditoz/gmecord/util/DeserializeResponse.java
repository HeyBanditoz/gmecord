package io.banditoz.gmecord.util;

import com.google.gson.Gson;
import io.banditoz.gmecord.api.Response;

public class DeserializeResponse {
    public static Response deserializeGivenString(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Response.class);
    }
}
