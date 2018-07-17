package com.healthstore.app.di.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthstore.app.FixVoidRespInterceptor;
import com.healthstore.app.FormedRequestInterceptor;
import com.healthstore.app.mvp.model.api.FeedbackService;
import com.healthstore.app.mvp.model.api.ItemService;
import com.healthstore.app.mvp.model.api.PicService;
import com.healthstore.app.mvp.model.api.TokenService;
import com.healthstore.app.mvp.model.api.UserService;
import com.healthstore.app.mvp.model.api.VipService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class ApiClientModule {

    public static final String serverUrl = "http://114.55.67.92:8080/app/api/";

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(FixVoidRespInterceptor fixVoidRespInterceptor, FormedRequestInterceptor formedRequestInterceptor){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(formedRequestInterceptor)
                .addInterceptor(logging)
                .addInterceptor(fixVoidRespInterceptor)
                .build();
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, ObjectMapper objectMapper) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(serverUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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

    @Provides
    @Singleton
    protected ItemService provideItemService(Retrofit retrofit){
        return retrofit.create(ItemService.class);
    }

    @Provides
    @Singleton
    protected PicService providePictureService(Retrofit retrofit){
        return retrofit.create(PicService.class);
    }

    @Provides
    @Singleton
    protected TokenService provideTokenService(Retrofit retrofit){
        return retrofit.create(TokenService.class);
    }

}
