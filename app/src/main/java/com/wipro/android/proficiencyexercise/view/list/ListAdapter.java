package com.wipro.android.proficiencyexercise.view.list;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
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
import com.wipro.android.proficiencyexercise.R;
import com.wipro.android.proficiencyexercise.model.Rows;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<Rows> rowsList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            desc = (TextView) view.findViewById(R.id.desc);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }


    public ListAdapter(Context mContext, List<Rows> rowsList) {
        this.rowsList = rowsList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Rows rows = rowsList.get(position);
        holder.title.setText(rows.getTitle());
        holder.desc.setText(rows.getDescription());

        if (rows.getImageHref() == null || rows.getImageHref().isEmpty()) {
            holder.image.setVisibility(View.GONE);
        } else {
            Glide.with(mContext)
                    .load(rows.getImageHref())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            holder.image.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            holder.image.setVisibility(View.VISIBLE);
                            return false;
                        }
                    })
                    .apply(new RequestOptions().placeholder(R.drawable.loading))
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return rowsList.size();
    }
}