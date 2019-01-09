package com.wipro.android.proficiencyexercise.view.list;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.wipro.android.proficiencyexercise.utils.Utils;
import com.wipro.android.proficiencyexercise.R;
import com.wipro.android.proficiencyexercise.data.remote.response.Row;
import com.wipro.android.proficiencyexercise.view.base.BaseActivity;
import com.wipro.android.proficiencyexercise.view.detail.DetailFragment;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private final List<Row> rowsList;
    private final Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView title, desc;
        private final ImageView image;
        private final CardView cardView;

        private MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            desc = view.findViewById(R.id.desc);
            image = view.findViewById(R.id.image);
            cardView = view.findViewById(R.id.cardView);
        }
    }

    public ListAdapter(Context mContext, List<Row> rowsList) {
        this.rowsList = rowsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Row rows = rowsList.get(position);
        holder.title.setText(rows.getTitle());
        holder.desc.setText(rows.getDescription());

        if (rows.getImageHref() == null || rows.getImageHref().isEmpty()) {
            holder.image.setVisibility(View.GONE);
        } else {
            loadImage(rows, holder.image);
        }

        holder.cardView.setOnClickListener(view ->
                Utils.INSTANCE.addFragment(((BaseActivity) mContext).getSupportFragmentManager(),
                        DetailFragment.newInstance(rows),
                        R.id.fragment_container)
        );
    }

    @Override
    public int getItemCount() {
        return rowsList.size();
    }

    private void loadImage(Row rows, ImageView imageView) {
        Glide.with(mContext)
                .load(rows.getImageHref())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        imageView.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        imageView.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .apply(new RequestOptions()
                        .override(250, 250) // here image width and size is hardcoded but it will be shown in center fit
                        .fitCenter())
                .into(imageView);
    }
}