package com.healthstore.app.injection.components;

import com.healthstore.app.api.FeedbackService;
import com.healthstore.app.api.UserService;
import com.healthstore.app.api.VipService;
import com.healthstore.app.injection.modules.ApiModule;
import com.healthstore.app.presenter.impl.BasePresenter;
import com.healthstore.app.presenter.impl.UserPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApiModule.class)
public interface ApiComponent {
    void inject(BasePresenter basePresenter);

}
