package com.healthstore.app.mvp.presenter;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthstore.app.AppManager;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.IPresenter;
import com.healthstore.app.mvp.contract.FeedbackContract;
import com.healthstore.app.mvp.model.api.FeedbackService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@FragmentScope
public class FeedbackPresenter implements IPresenter {

    @Inject FeedbackContract.View view;
    @Inject FeedbackContract.Model model;
    @Inject FeedbackService feedbackService;
    @Inject ObjectMapper objectMapper;
    @Inject AppManager appManager;

    @Inject
    public FeedbackPresenter(){}

    @Override public void onStart() {

    }

    @Override public void onDestroy() {

    }

    public void submit(String text) {
        feedbackService.submitFeedback(appManager.getMainUser().getId(), text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.isSucceed())
                        view.onSubmitSuccess();
                    else
                        view.onSubmitFailed();
                    String respString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
                    Log.d("submitFeedback", respString);
                });
    }

}
