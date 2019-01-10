package com.wipro.android.proficiencyexercise;

import android.app.Application;

import com.wipro.android.proficiencyexercise.di.components.AppComponent;
import com.wipro.android.proficiencyexercise.di.components.DaggerAppComponent;
import com.wipro.android.proficiencyexercise.di.modules.AppModule;

public class WiproApp extends Application {

    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createInjector();
        appComponent.inject(this);
    }

    private void createInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }
}
