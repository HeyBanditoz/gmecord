package io.banditoz.gmecord;

import io.banditoz.gmecord.api.Payload;
import io.banditoz.gmecord.util.SerializerDeserializer;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class GroupmeImageUploader {
    private OkHttpClient client;
    private Logger logger;

    public GroupmeImageUploader() {
        client = new OkHttpClient();
        logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
    }

    /**
     * Uploads an image URL to Groupme (because they require it for some reason only God knows. Why they just can't
     * proxy like Discord does is beyond me, but whatever.)
     * @param url The image URL to upload to Groupme.
     * @return String of the image URL.
     * @throws IOException If something went wrong getting the image.
     */
    public String uploadImage(String url) throws IOException {
        logger.debug("Starting upload of image " + url);
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        InputStream inputStream = Objects.requireNonNull(response.body()).byteStream();
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), inputStream.readAllBytes());
        request = new Request.Builder()
                .url("https://image.groupme.com/pictures")
                .post(requestBody)
                .header("X-Access-Token", SettingsManager.getInstance().getSettings().getGroupMeToken())
                .header("Content-Type", "image/png")
                .build();
        Payload image = null;
        inputStream.close();
        try {
            response = client.newCall(request).execute();
            String responseBody = Objects.requireNonNull(response.body()).string();
            image = SerializerDeserializer.deserializeImageGivenString(responseBody);
        } catch (IOException e) {
            logger.error("Error while uploading image!", e);
        }
        return image.getPictureUrl();
    }
}
