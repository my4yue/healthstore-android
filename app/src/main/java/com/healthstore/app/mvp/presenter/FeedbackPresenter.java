package com.healthstore.app.mvp.presenter;

import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.IPresenter;
import com.healthstore.app.mvp.contract.FeedbackContract;

import javax.inject.Inject;

@FragmentScope
public class FeedbackPresenter implements IPresenter {

    @Inject FeedbackContract.View view;
    @Inject FeedbackContract.Model model;

    @Inject
    public FeedbackPresenter(){}

    @Override public void onStart() {

    }

    @Override public void onDestroy() {

    }

    public void submit(String text) {

    }

}
