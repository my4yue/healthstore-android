package com.healthstore.app.di.module;

import android.content.Context;

import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.contract.AgendaContract;
import com.healthstore.app.mvp.ui.activity.AppActivity;
import com.healthstore.app.mvp.ui.fragment.AgendaIndexFragment;
import com.healthstore.app.mvp.ui.fragment.AppFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class AgendaModule {

    AppFragment appFragment;
    AppActivity appActivity;

    public AgendaModule(AppFragment appFragment) {
        this.appFragment = appFragment;
        this.appActivity = (AppActivity) appFragment.getActivity();
    }

    @FragmentScope
    @Provides
    public AppFragment provideAppFragment() {
        return appFragment;
    }

    @FragmentScope
    @Provides
    public AppActivity provideAppActivity() {
        return appActivity;
    }

    @FragmentScope
    @Provides
    public Context provideContext() {
        return appActivity;
    }

    @FragmentScope
    @Provides
    public AgendaContract.View provideAgendaView(){
        return (AgendaContract.View) appFragment;
    }

}
