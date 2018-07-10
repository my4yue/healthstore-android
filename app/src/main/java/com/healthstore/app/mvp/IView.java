package com.healthstore.app.mvp;

import android.support.annotation.NonNull;

import com.healthstore.app.di.scope.ActivityScope;

import javax.inject.Inject;
import javax.inject.Singleton;

public interface IView {

    default void showLoading(){}

    default void hideLoading(){}

    default void showMessage(@NonNull String message) {}

}
