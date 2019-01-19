package com.wipro.android.proficiencyexercise.di.components

import com.wipro.android.proficiencyexercise.WiproApp
import com.wipro.android.proficiencyexercise.di.modules.AppModule
import com.wipro.android.proficiencyexercise.view.list.ListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(appApplication: WiproApp)

    fun inject(listFragment: ListFragment)
}
