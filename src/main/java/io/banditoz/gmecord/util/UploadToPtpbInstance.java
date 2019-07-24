package io.banditoz.gmecord.util;

import io.banditoz.gmecord.Bot;
import io.banditoz.gmecord.SettingsManager;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import javax.naming.ServiceUnavailableException;
import java.io.IOException;

public class UploadToPtpbInstance {
    private final static String ENDPOINT = SettingsManager.getInstance().getSettings().getPtpbEndpoint() + "?u=1";

    /**
     * Upload a message to the configured ptpb instance, if configured.
     * @param message The message to send.
     * @return The URL of the uploaded message.
     * @throws IOException If the request fails for some reason.
     */
    public static String uploadToPtpb(String message) throws IOException, ServiceUnavailableException {
        Response response;
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("c", message)
                .build();

        Request request = new Request.Builder()
                .url(ENDPOINT)
                .post(requestBody)
                .build();

        response = Bot.client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new ServiceUnavailableException("For some reason, the configured ptpb endpoint is not working!");
        }
        return response.body().string().replace("\n", "").replace("\r", "");
    }
}
