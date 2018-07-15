package com.healthstore.app.di.module;

import android.content.Context;

import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.contract.PictureContract;
import com.healthstore.app.mvp.ui.activity.AppActivity;
import com.healthstore.app.mvp.ui.fragment.AppFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class PictureModule {

    Context context;
    PictureContract.View view;

    public PictureModule(PictureContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Provides
    @FragmentScope
    Context provideContext() {
        return context;
    }

    @Provides
    @FragmentScope
    PictureContract.View providePictureView() {
        return view;
    }

    @Provides @FragmentScope
    AppFragment provideAppFragment(){
        return (AppFragment) view;
    }
    @Provides @FragmentScope
    AppActivity provideAppActivity(){
        return (AppActivity) context;
    }

}
