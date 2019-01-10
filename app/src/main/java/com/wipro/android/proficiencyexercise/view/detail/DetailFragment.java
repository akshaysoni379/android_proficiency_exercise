package com.wipro.android.proficiencyexercise.view.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wipro.android.proficiencyexercise.R;
import com.wipro.android.proficiencyexercise.data.remote.response.Row;
import com.wipro.android.proficiencyexercise.view.base.BaseFragment;

public class DetailFragment extends BaseFragment {

    private TextView desc;
    private ImageView image;
    private Row rows;

    public static DetailFragment newInstance(Row rows) {
        Bundle args = new Bundle();
        args.putSerializable("rows", rows);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rows = (Row) (getArguments() != null ? getArguments().getSerializable("rows") : null);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        desc = view.findViewById(R.id.desc);
        image = view.findViewById(R.id.image);
        setViewData();
        return view;
    }

    private void setViewData() {
        if (rows != null) {
            if (rows.getTitle() != null && !rows.getTitle().isEmpty())
                if (getBaseActivity().getSupportActionBar() != null) {
                    getBaseActivity().getSupportActionBar().setTitle(rows.getTitle());
                }
            if (rows.getDescription() != null && !rows.getDescription().isEmpty())
                desc.setText(rows.getDescription());
            if (rows.getImageHref() != null && !rows.getImageHref().isEmpty())
                Glide.with(getBaseActivity())
                        .load(rows.getImageHref())
                        .into(image);
        }
    }
}