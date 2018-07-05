package com.healthstore.app.di.module;

import com.healthstore.app.di.scope.ActivityScope;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.model.UserModel;
import com.healthstore.app.mvp.model.api.UserService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class UserModule {

    UserContract.View mView;

    public UserModule(UserContract.View view) {
        mView = view;
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

}
