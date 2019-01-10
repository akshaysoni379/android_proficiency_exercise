package com.wipro.android.proficiencyexercise.di.components;


import com.wipro.android.proficiencyexercise.WiproApp;
import com.wipro.android.proficiencyexercise.di.modules.AppModule;
import com.wipro.android.proficiencyexercise.view.list.ListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(WiproApp appApplication);

    void inject(ListFragment listFragment);
}
