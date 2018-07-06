package com.healthstore.app.di.module;

import android.content.Context;

import com.healthstore.app.di.scope.ActivityScope;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.model.UserModel;
import com.healthstore.app.mvp.model.api.UserService;
import com.healthstore.app.mvp.ui.adapter.UserFuncIndexAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class UserModule {

    UserContract.View mView;
    Context mContext;

    public UserModule(UserContract.View view, Context context) {
        mView = view;
        mContext = context;
    }

    @Provides @FragmentScope UserContract.View provideUserView(){
        return mView;
    }

    @Provides @FragmentScope UserContract.Model provideUserModel(UserModel userModel){
        return userModel;
    }

    @Provides @FragmentScope UserService provideUserService(Retrofit retrofit){
        return retrofit.create(UserService.class);
    }

    @Provides @FragmentScope Context provideContext(){
        return mContext;
    }

}
