package com.healthstore.app;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentController;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthstore.app.mvp.model.api.UserService;
import com.healthstore.app.mvp.model.entity.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class AppManager {

    private List<Activity> activities = new ArrayList<>();
    private Activity mCurrentActivity;

    @Inject Application mApplication;
    @Inject UserService mUserService;
    @Inject ObjectMapper mObjectMapper;

    Toast mToast;
    MutableLiveData<User> mUser = new MutableLiveData<>();

    @Inject
    public AppManager() {

    }

    public void syncMainUser(){
        mUserService.getUserById(1L)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    String userString = mObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
                    Log.d("syncMainUser", userString);
                    mUser.setValue(user);
                });
    }

    public void startActivity(Context ctx, Class activityClass) {
        Intent intent = new Intent(ctx, activityClass);
        ctx.startActivity(intent);
    }

    public void showToast(String message) {
        if (mToast == null)
            mToast = Toast.makeText(mApplication, "", Toast.LENGTH_SHORT);
        mToast.setText(message);
        mToast.show();
    }

    public MutableLiveData<User> getMainUser() {
        return mUser;
    }

}
