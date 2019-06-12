/*package io.banditoz.gmecord;

import io.banditoz.gmecord.api.ImagePayload;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GroupmeImageUploader {
    private ImagePayload payload;
    private static int imageCount = 0;
    public GroupmeImageUploader() {
        imageCount++;
    }

    public void uploadToGroupme(String Url) throws IOException {
        // First, download the image from the URL.
        FileOutputStream outStream = null;
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(new HttpGet(Url));
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            outStream = new FileOutputStream("/tmp/" + imageCount);
            entity.writeTo(outStream);
        }
        outStream.close();

        // Then, upload it to GroupMe.
        client = HttpClients.createDefault();

        File file = new File("/tmp/" + imageCount);

        HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532)
                .addBinaryBody("1.jpg", file, ContentType.DEFAULT_BINARY, file.getName())
                .build();
        HttpUriRequest req = RequestBuilder.post("https://image.groupme.com/pictures").setEntity(data).build();
        client.execute(req);
    }

    private String getUploadedUrl() {
        return payload.getPictureUrl();
    }
}
*/