package com.wipro.android.proficiencyexercise.view.base;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wipro.android.proficiencyexercise.R;

public abstract class BaseFragment extends Fragment {

    protected ProgressDialog dialog;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage(getString(R.string.please_wait));
        dialog.setCancelable(false);
        return textView;
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }
}
