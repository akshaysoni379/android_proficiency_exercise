package com.wipro.android.proficiencyexercise.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.wipro.android.proficiencyexercise.AppUtil.NavigationUtils;
import com.wipro.android.proficiencyexercise.R;
import com.wipro.android.proficiencyexercise.view.base.BaseActivity;
import com.wipro.android.proficiencyexercise.view.list.ListFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

        NavigationUtils.addFragment(getSupportFragmentManager(),
                new ListFragment(),
                R.id.fragment_container);
    }

}