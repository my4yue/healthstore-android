package com.healthstore.app.di.component;

import android.content.Context;

import com.healthstore.app.di.module.UserModule;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.contract.UserContract;
import com.healthstore.app.mvp.ui.fragment.AppFragment;
import com.healthstore.app.mvp.ui.fragment.UserDetailsFragment;
import com.healthstore.app.mvp.ui.fragment.UserFeedbackFragment;
import com.healthstore.app.mvp.ui.fragment.UserIndexFragment;
import com.healthstore.app.mvp.ui.fragment.UserItemSelectorFragment;
import com.healthstore.app.mvp.ui.fragment.UserSettingsFragment;

import dagger.BindsInstance;
import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = UserModule.class)
public interface UserComponent {

    void inject(UserIndexFragment userIndexFragment);
    void inject(UserDetailsFragment userDetailsFragment);
    void inject(UserSettingsFragment userSettingsFragment);
    void inject(UserItemSelectorFragment userItemSelectorFragment);

}
