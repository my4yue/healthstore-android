package com.healthstore.app.di.component;

import com.healthstore.app.di.module.AgendaModule;
import com.healthstore.app.di.scope.FragmentScope;
import com.healthstore.app.mvp.ui.fragment.AgendaIndexFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = AgendaModule.class)
public interface AgendaComponent {

    void inject(AgendaIndexFragment agendaIndexFragment);

}
