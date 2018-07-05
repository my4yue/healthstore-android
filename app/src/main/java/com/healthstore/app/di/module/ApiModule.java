package com.healthstore.app.di.module;

import com.healthstore.app.mvp.model.api.FeedbackService;
import com.healthstore.app.mvp.model.api.UserService;
import com.healthstore.app.mvp.model.api.VipService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    public static final String serverUrl = "http://healthtest:8080/app/api/";

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .build();
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(serverUrl)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    protected UserService provideUserService(Retrofit retrofit){
        return retrofit.create(UserService.class);
    }

    @Provides
    @Singleton
    protected FeedbackService provideFeedbackService(Retrofit retrofit){
        return retrofit.create(FeedbackService.class);
    }

    @Provides
    @Singleton
    protected VipService provideVipService(Retrofit retrofit){
        return retrofit.create(VipService.class);
    }


}
