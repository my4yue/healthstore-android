package com.healthstore.app.mvp;

import com.healthstore.app.di.scope.ActivityScope;

import javax.inject.Inject;
import javax.inject.Singleton;

public interface IPresenter {

    void onStart();
    void onDestroy();

    @ActivityScope
    class Empty implements IPresenter{

        @Inject
        public Empty(){}

        @Override
        public void onStart() {

        }

        @Override
        public void onDestroy() {

        }
    }
}
