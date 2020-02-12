package io.banditoz.gmecord.paste;

import io.banditoz.gmecord.Bot;
import io.banditoz.gmecord.util.SerializerDeserializer;
import okhttp3.*;

import java.io.IOException;

public class PasteggUploader {
    private final Paste paste;
    private static final MediaType MEDIA_TYPE_JSON = MediaType.get("application/json; charset=utf-8");

    public PasteggUploader(Paste paste) {
        this.paste = paste;
    }

    /**
     * Uploads a message to paste.gg.
     * @return The URL of the paste.
     */
    public String uploadToPastegg() throws IOException {
        String responseString;
        Response response;
        RequestBody body = RequestBody.create(SerializerDeserializer.serializePaste(paste), MEDIA_TYPE_JSON);
        Request request = new Request.Builder()
                .url("https://api.paste.gg/v1/pastes/")
                .post(body)
                .build();

        response = Bot.getOkHttpClient().newCall(request).execute();
        responseString = response.body().string();
        return buildUrl(SerializerDeserializer.deserializePasteResponseGivenString(responseString));
    }

    private String buildUrl(PasteResponse pr) {
        String id = pr.getResult().getId();
        String fileId = pr.getResult().getFiles().get(0).getId();

        return "https://paste.gg/p/anonymous/" + id + "/files/" + fileId + "/raw";
    }
}
