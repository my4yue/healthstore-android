package com.healthstore.app.di.component;

import com.healthstore.app.di.module.FeedbackModule;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.ui.fragment.UserFeedbackFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FeedbackModule.class)
public interface FeedbackComponent {

    void inject(UserFeedbackFragment userFeedbackFragment);

}
