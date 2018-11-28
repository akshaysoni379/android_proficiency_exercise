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
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        /**
         * Added fragment
         */
        NavigationUtils.addFragment(getSupportFragmentManager(),
                new ListFragment(),
                R.id.fragment_container);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            moveTaskToBack(true);
        } else {
            super.onBackPressed();
        }
    }
}