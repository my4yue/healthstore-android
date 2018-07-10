package com.healthstore.app.mvp.model;

import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.contract.FeedbackContract;

import javax.inject.Inject;

@FragmentScope
public class FeedbackModel implements FeedbackContract.Model{

    @Inject public FeedbackModel(){

    }

    @Override public void onDestroy() {

    }

    @Override public void submitFeedback(String text) {

    }
}
