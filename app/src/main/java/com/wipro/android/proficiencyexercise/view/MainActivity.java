package com.wipro.android.proficiencyexercise.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toolbar;

import com.wipro.android.proficiencyexercise.AppUtil.LogUtil;
import com.wipro.android.proficiencyexercise.R;
import com.wipro.android.proficiencyexercise.WiproApp;
import com.wipro.android.proficiencyexercise.model.CanadaList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(tb);

        getDataFromApi();
    }

    private void getDataFromApi() {
        Call<CanadaList> call = WiproApp.getServiceAPI().getData();
        call.enqueue(new Callback<CanadaList>() {
            @Override
            public void onResponse(Call<CanadaList> call, Response<CanadaList> response) {
                CanadaList canadaList = response.body();
                if (canadaList != null && canadaList.getRows() != null && canadaList.getRows().size() > 0)
                    LogUtil.e(TAG, "size: " + canadaList.getRows().size());
            }

            @Override
            public void onFailure(Call<CanadaList> call, Throwable t) {

            }
        });
    }
}