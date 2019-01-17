package com.wipro.android.proficiencyexercise

import android.app.Application

import com.wipro.android.proficiencyexercise.di.components.AppComponent
import com.wipro.android.proficiencyexercise.di.components.DaggerAppComponent
import com.wipro.android.proficiencyexercise.di.modules.AppModule

class WiproApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}
