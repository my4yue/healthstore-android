package com.healthstore.app.di.component;

import com.healthstore.app.di.module.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApiModule.class)
public interface ApiComponent {
//    void inject(BasePresenter basePresenter);

}
