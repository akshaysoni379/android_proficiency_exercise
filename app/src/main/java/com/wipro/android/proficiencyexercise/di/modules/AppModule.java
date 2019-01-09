package com.wipro.android.proficiencyexercise.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wipro.android.proficiencyexercise.WiproApp;
import com.wipro.android.proficiencyexercise.domain.repository.Repository;
import com.wipro.android.proficiencyexercise.domain.repository.RepositoryImpl;
import com.wipro.android.proficiencyexercise.netcomm.ApiConstant;
import com.wipro.android.proficiencyexercise.netcomm.ServiceAPI;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private final WiproApp application;
    String BASE_URL;

    public AppModule(WiproApp application, String BASE_URL) {
        this.application = application;
        this.BASE_URL = BASE_URL;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    @Singleton
    public ServiceAPI provideApiInterface(Retrofit retrofit) {
        return retrofit.create(ServiceAPI.class);
    }

    @Provides
    @Singleton
    public Repository provideRepository(ServiceAPI apiInterface) {
        return new RepositoryImpl(apiInterface);
    }

}