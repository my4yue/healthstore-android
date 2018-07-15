package com.healthstore.app.di.component;

import com.healthstore.app.di.module.PictureModule;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.ui.fragment.PictureSelectorFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = PictureModule.class)
public interface PictureComponent {

    void inject(PictureSelectorFragment pictureSelectorFragment);

}
