package com.wipro.android.proficiencyexercise.view;

import android.os.Bundle;
import android.widget.Toolbar;

import com.wipro.android.proficiencyexercise.AppUtil.BaseActivity;
import com.wipro.android.proficiencyexercise.AppUtil.NavigationUtils;
import com.wipro.android.proficiencyexercise.R;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(tb);

        NavigationUtils.addFragment(getSupportFragmentManager(),
                new ListFragment(),
                R.id.fragment_container);
    }

}