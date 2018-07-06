package com.healthstore.app.di.component;

import android.content.Context;

import com.healthstore.app.di.module.UserModule;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.ui.fragment.UserDetailsFragment;
import com.healthstore.app.mvp.ui.fragment.UserIndexFragment;

import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = UserModule.class)
public interface UserComponent {

    void inject(UserIndexFragment userIndexFragment);
    void inject(UserDetailsFragment userDetailsFragment);

//    Context context();
//
//    interface Builder{
//        @BindsInstance
//        Context context(Context context);
//
//        UserComponent build();
//    }

}
