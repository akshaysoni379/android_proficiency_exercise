package com.wipro.android.proficiencyexercise;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wipro.android.proficiencyexercise.netcomm.ApiConstant;
import com.wipro.android.proficiencyexercise.netcomm.ServiceAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WiproApp extends Application {

    private static Retrofit retrofit;
    private static ServiceAPI serviceAPI;

    @Override
    public void onCreate() {
        super.onCreate();

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static ServiceAPI getServiceAPI() {
        if (serviceAPI == null) {
            serviceAPI = retrofit.create(ServiceAPI.class);
        }
        return serviceAPI;
    }
}
