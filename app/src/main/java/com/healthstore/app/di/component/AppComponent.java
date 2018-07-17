package com.healthstore.app.di.component;

import android.app.Application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthstore.app.AppDelegate;
import com.healthstore.app.AppManager;
import com.healthstore.app.ImageLoader;
import com.healthstore.app.di.module.ApiClientModule;
import com.healthstore.app.di.module.AppModule;
import com.healthstore.app.mvp.model.api.FeedbackService;
import com.healthstore.app.mvp.model.api.ItemService;
import com.healthstore.app.mvp.model.api.PicService;
import com.healthstore.app.mvp.model.api.TokenService;
import com.healthstore.app.mvp.model.api.UserService;
import com.healthstore.app.mvp.model.cache.ItemCache;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, ApiClientModule.class})
public interface AppComponent {

    void inject(AppDelegate appDelegate);

    Application application();

    AppManager appManager();
    ItemCache itemCache();

    ObjectMapper objectMapper();

    Retrofit retrofit();

    ImageLoader imageLoader();

    UserService userService();
    FeedbackService feedbackService();
    ItemService itemService();
    PicService picService();
    TokenService tokenService();

//    IPresenter.Empty empty();

//    @Component.Builder
//    interface Builder{
//        @BindsInstance
//        Builder application(Application application);
//
//        AppComponent build();
//    }

}
