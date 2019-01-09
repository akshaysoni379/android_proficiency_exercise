package com.wipro.android.proficiencyexercise;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wipro.android.proficiencyexercise.di.components.AppComponent;
import com.wipro.android.proficiencyexercise.di.components.DaggerAppComponent;
import com.wipro.android.proficiencyexercise.di.modules.AppModule;
import com.wipro.android.proficiencyexercise.netcomm.ApiConstant;
import com.wipro.android.proficiencyexercise.netcomm.ServiceAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WiproApp extends Application {

    //private static Retrofit retrofit;
    //private static ServiceAPI serviceAPI;
    private AppComponent appComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        createInjector();
        appComponent.inject(this);

        /*Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();*/
    }

   /* public static ServiceAPI getServiceAPI() {
        if (serviceAPI == null) {
            serviceAPI = retrofit.create(ServiceAPI.class);
        }
        return serviceAPI;
    }*/

    public void createInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, ApiConstant.BASE_URL))
                .build();
    }
}
