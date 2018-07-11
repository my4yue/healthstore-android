package com.healthstore.app.di.module;

import android.content.Context;

import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.contract.FeedbackContract;
import com.healthstore.app.mvp.model.FeedbackModel;
import com.healthstore.app.mvp.model.api.FeedbackService;
import com.healthstore.app.mvp.ui.activity.AppActivity;
import com.healthstore.app.mvp.ui.fragment.AppFragment;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class FeedbackModule {

    FeedbackContract.View mView;
    Context mContext;

    public FeedbackModule(FeedbackContract.View view, Context context){
        this.mView = view;
        this.mContext = context;
    }

    @FragmentScope @Provides FeedbackContract.View provideFeedbackView(){
        return mView;
    }

    @FragmentScope @Provides FeedbackContract.Model provideFeedbackModel(FeedbackModel model){
        return model;
    }

    @FragmentScope @Provides FeedbackService provideFeedbackService(Retrofit retrofit) {
        return retrofit.create(FeedbackService.class);
    }

    @Provides @FragmentScope AppFragment provideAppFragment(){
        return (AppFragment) mView;
    }
    @Provides @FragmentScope AppActivity provideAppActivity(){
        return (AppActivity) mContext;
    }

}
