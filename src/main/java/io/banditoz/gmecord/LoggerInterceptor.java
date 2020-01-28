package io.banditoz.gmecord;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoggerInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public Response intercept(Chain chain) throws IOException {
        // https://square.github.io/okhttp/interceptors/
        Request request = chain.request();

        long t1 = System.nanoTime();
        String requestString = stringifyRequestBody(request);
        logger.debug("Sending request " + request.url() + " with body " +
                ((requestString.equals("")) ? "<no body>" : requestString) +
                " and with headers " + ((request.headers().size() == 0) ? "<no headers>" : "\n" + request.headers() + "\n"));

        Response response = chain.proceed(request);
        String responseString = stringifyResponseBody(response);

        long t2 = System.nanoTime();
        logger.debug("Received response for " + response.request().url() + " with body " +
                ((responseString.equals("") || responseString.equals(" ")) ? "<no body> " : responseString) +
                " in " + (t2 - t1) / 1e6d + " ms and with headers " +
                ((response.headers().size() == 0) ? "<no headers>" : "\n" + response.headers()));

        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), responseString)).build();
    }

    private String stringifyRequestBody(Request request) {
        Buffer buffer = new Buffer();
        if (request.body() != null) {
            try {
                Request copy = request.newBuilder().build();
                copy.body().writeTo(buffer);
                return buffer.readUtf8();
            } catch (Exception e) {
                logger.warn("Failed to stringify request body.", e);
            } finally {
                buffer.close();
            }
        }
        return "";
    }

    private String stringifyResponseBody(Response response) {
        String responseString = "";
        try {
            responseString = response.newBuilder().build().body().string();
        } catch (Exception e) {
            logger.warn("Failed to stringify response body.", e);
        }
        return responseString;
    }
}
