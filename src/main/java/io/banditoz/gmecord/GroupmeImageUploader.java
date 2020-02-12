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
    private final static Logger logger = LoggerFactory.getLogger(GroupmeImageUploader.class);

    /**
     * Uploads an image URL to Groupme (because they require it for some reason only God knows. Why they just can't
     * proxy like Discord does is beyond me, but whatever.) Update: apparently they DO with the postprocessing
     * attachment type, but we will just do this so the image are more permanent.
     * @param url The image URL to upload to Groupme.
     * @return String of the image URL.
     * @throws IOException If something went wrong getting the image.
     */
    public static String uploadImage(String url) throws IOException {
        InputStream inputStream = downloadImage(url);
        RequestBody requestBody = RequestBody.create(inputStream.readAllBytes(), MediaType.parse("image/png"));
        Request request = new Request.Builder()
                .url("https://image.groupme.com/pictures")
                .post(requestBody)
                .header("X-Access-Token", SettingsManager.getInstance().getSettings().getGroupMeToken())
                .header("Content-Type", "image/png")
                .build();
        Payload image = null;
        inputStream.close();
        try {
            Response response = Bot.getOkHttpClient().newCall(request).execute();
            String responseBody = Objects.requireNonNull(response.body()).string();
            image = SerializerDeserializer.deserializeImageGivenString(responseBody);
            response.close();
        } catch (IOException e) {
            logger.error("Error while uploading image!", e);
        }
        return image.getPictureUrl();
    }

    /**
     * Downloads an image given the URL and returns an InputStream of its bytes.
     * @param url The image URL to attempt to download.
     * @return An InputStream of the image.
     * @throws IOException If something went wrong while downloading the image.
     * @throws IllegalArgumentException If the URL we attempted wasn't actually an image (different Content-Type)
     */
    public static InputStream downloadImage(String url) throws IOException, IllegalArgumentException {
        Request request = new Request.Builder()
                .url(url)
                .head() // first, check if the URL is actually an image
                .build();
        Response response = Bot.getOkHttpClient().newCall(request).execute();
        if (response.header("Content-Type").startsWith("image/")) {
            request = new Request.Builder()
                    .url(url)
                    .build();
            response = Bot.getOkHttpClient().newCall(request).execute();
        }
        else {
            throw new IllegalArgumentException("URL to download wasn't actually in image!");
        }
        return Objects.requireNonNull(response.body()).byteStream();
    }
}
