package com.healthstore.app;

import android.support.annotation.Nullable;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;

@Singleton
public class FormedRequestInterceptor implements Interceptor {

    @Inject ObjectMapper objectMapper;

    @Inject public FormedRequestInterceptor() { }

    @Override public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        RequestBody body = request.body();
        if (body != null && body.contentType().subtype().contains("json")) {
            Buffer buf = new Buffer();
            request.body().writeTo(buf);
            String stringBody = buf.readString(Charset.forName("utf-8"));
            Map<String, Object> map = objectMapper.readValue(stringBody, Map.class);

            stringBody = map.keySet().stream().map(k -> k + "=" + map.get(k).toString() + "&").collect(Collectors.joining());
            String newStringBody = stringBody.substring(0, stringBody.lastIndexOf("&"));

            //fix bug watchword大小写问题
            newStringBody = newStringBody.replaceAll("watchword", "watchWord");
            final byte[] bytes = newStringBody.getBytes("utf-8");

            request = request.newBuilder().patch(new RequestBody() {
                @Nullable @Override public MediaType contentType() {
                    return MediaType.parse("application/x-www-form-urlencoded");
                }

                @Override public void writeTo(BufferedSink sink) throws IOException {
                    sink.write(bytes);
                }
            }).build();
        }

        return chain.proceed(request);
    }
}
