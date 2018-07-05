package com.healthstore.app.mvp;

import android.support.annotation.NonNull;

public interface IView {

    default void showLoading(){}

    default void hideLoading(){}

    void showMessage(@NonNull String message);

}
