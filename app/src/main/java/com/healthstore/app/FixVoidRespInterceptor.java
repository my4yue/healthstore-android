package com.healthstore.app;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;

@Singleton
public class FixVoidRespInterceptor implements Interceptor {

    @Inject
    public FixVoidRespInterceptor(){

    }

    @Override public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        long contentLength = response.body().contentLength();
        if (contentLength == 0) {
            Buffer buf = new Buffer();
            buf.write("{}".getBytes("utf-8"));
            response = response.newBuilder()
                    .body(new RealResponseBody("", 2, buf))
                    .build();
        }

        return response;
    }
}
