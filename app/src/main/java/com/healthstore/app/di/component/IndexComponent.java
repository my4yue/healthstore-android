package com.healthstore.app.di.component;

import android.app.Application;

import com.healthstore.app.ActivityManager;
import com.healthstore.app.di.scope.ActivityScope;
import com.healthstore.app.mvp.IPresenter;
import com.healthstore.app.mvp.ui.activity.AppActivity;
import com.healthstore.app.mvp.ui.activity.IndexActivity;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class)
public interface IndexComponent {

    void inject(IndexActivity indexActivity);

    IPresenter.Empty empty();

}
