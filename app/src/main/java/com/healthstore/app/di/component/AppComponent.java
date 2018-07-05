package com.healthstore.app.di.component;

import android.app.Application;

import com.healthstore.app.AppDelegate;
import com.healthstore.app.AppManager;
import com.healthstore.app.di.module.ApiClientModule;
import com.healthstore.app.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiClientModule.class})
public interface AppComponent {

    void inject(AppDelegate appDelegate);

    Application application();

    AppManager appManager();

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}
