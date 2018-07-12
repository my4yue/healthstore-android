package com.healthstore.app.mvp.contract;

import com.healthstore.app.mvp.IModel;
import com.healthstore.app.mvp.IView;
import com.healthstore.app.mvp.model.entity.User;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface UserContract {

    interface View extends IView {
//        void onUserUpdated(User user);
    }

    interface Model extends IModel {
        Observable<User> getUserById(long userId);
        Completable updateUser(long userId, User user);
    }

}
