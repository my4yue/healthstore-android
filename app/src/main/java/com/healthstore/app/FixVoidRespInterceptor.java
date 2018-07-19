package com.healthstore.app;

import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSource;

@Singleton
public class FixVoidRespInterceptor implements Interceptor {

    @Inject ObjectMapper objectMapper;

    @Inject
    public FixVoidRespInterceptor() {

    }

    @Override public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        ResponseBody body = response.body();
        long contentLength = body.contentLength();
        if (contentLength > 0) {
            // 读取response流
            BufferedSource source = response.body().source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            String stringResp = buffer.clone().readString(Charset.forName("utf-8"));
            stringResp = stringResp.replaceAll("watchword", "watchWord");
            JsonNode tree = objectMapper.readTree(stringResp);
            Log.d("LoggingInterceptor", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree));
            if (tree.isObject()) {
                if (tree.has("errorCode")) {
                    String errorCode = tree.get("errorCode").asText();
                    String errorMessage = tree.get("errorMessage").asText();
                    Buffer buf = new Buffer();
                    buf.write("".getBytes("utf-8"));
                    response = response.newBuilder()
                            .code(400)
                            .addHeader("errorMessage", errorMessage)
                            .addHeader("errorCode", errorCode)
                            .body(new RealResponseBody("", 0, buf))
                            .build();
                }
            }

        }

        return response;
    }
}
