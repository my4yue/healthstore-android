package com.healthstore.app.di.module;

import android.content.Context;

import com.healthstore.app.di.scope.ActivityScope;
import com.healthstore.app.mvp.contract.IndexContract;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class IndexModule {

    private IndexContract.View view;

    public IndexModule(IndexContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    Context provideContext(){
        return (Context) view;
    }

    @Provides
    @ActivityScope
    IndexContract.View provideIndexView(){
        return view;
    }

}
