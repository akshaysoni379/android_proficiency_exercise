package com.wipro.android.proficiencyexercise.di.modules

import com.google.gson.GsonBuilder
import com.wipro.android.proficiencyexercise.data.remote.api.ServiceAPI
import com.wipro.android.proficiencyexercise.domain.repository.Repository
import com.wipro.android.proficiencyexercise.domain.repository.RepositoryImpl
import com.wipro.android.proficiencyexercise.utils.Constants
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ServiceAPI {
        return retrofit.create(ServiceAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiInterface: ServiceAPI): Repository {
        return RepositoryImpl(apiInterface)
    }

}