package io.banditoz.gmecord.paste;

import io.banditoz.gmecord.Bot;
import io.banditoz.gmecord.util.SerializerDeserializer;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PasteggUploader {
    private Paste paste;
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final Logger logger;

    public PasteggUploader(Paste paste) {
        this.paste = paste;
        logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
    }

    /**
     * Uploads a message to paste.gg.
     * @return The URL of the paste.
     */
    public String uploadToPastegg() throws IOException {
        String responseString;
        Response response;
        String json = SerializerDeserializer.serializePaste(this.paste);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("https://api.paste.gg/v1/pastes/")
                .post(body)
                .build();

        response = Bot.client.newCall(request).execute();
        responseString = response.body().string();
        logger.debug("Response string: " + responseString);
        return buildUrl(SerializerDeserializer.deserializePasteResponseGivenString(responseString));
    }

    private String buildUrl(PasteResponse pr) {
        String id = pr.getResult().getId();
        String fileId = pr.getResult().getFiles().get(0).getId();

        return "https://paste.gg/p/anonymous/" + id + "/files/" + fileId + "/raw";
    }
}
