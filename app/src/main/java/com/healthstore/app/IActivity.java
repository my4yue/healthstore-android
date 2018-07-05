package com.healthstore.app;

import android.support.annotation.NonNull;

import com.healthstore.app.di.component.AppComponent;

public interface IActivity {

    void setUpActivityComponent(@NonNull AppComponent appComponent);

}
