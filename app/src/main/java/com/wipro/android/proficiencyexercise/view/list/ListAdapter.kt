package com.wipro.android.proficiencyexercise.view.list

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.wipro.android.proficiencyexercise.R
import com.wipro.android.proficiencyexercise.data.remote.response.Row
import com.wipro.android.proficiencyexercise.utils.Utils
import com.wipro.android.proficiencyexercise.view.base.BaseActivity
import com.wipro.android.proficiencyexercise.view.detail.DetailFragment
import kotlin.properties.Delegates

class ListAdapter(private val mContext: Context, private val rowsList: List<Row>) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView by Delegates.notNull()
        var desc: TextView by Delegates.notNull()
        var image: ImageView by Delegates.notNull()
        var cardView: CardView by Delegates.notNull()

        init {
            title = view.findViewById(R.id.title)
            desc = view.findViewById(R.id.desc)
            image = view.findViewById(R.id.image)
            cardView = view.findViewById(R.id.cardView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val rows = rowsList[position]
        holder.title.text = rows.title
        holder.desc.text = rows.description

        if (rows.imageHref == null || rows.imageHref!!.isEmpty()) {
            holder.image.visibility = View.GONE
        } else {
            loadImage(rows, holder.image)
        }

        holder.cardView.setOnClickListener {
            Utils.addFragment((mContext as BaseActivity).supportFragmentManager,
                    DetailFragment.newInstance(rows),
                    R.id.fragment_container)
        }
    }

    override fun getItemCount(): Int {
        return rowsList.size
    }

    private fun loadImage(rows: Row, imageView: ImageView) {
        Glide.with(mContext)
                .load(rows.imageHref)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        imageView.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        imageView.visibility = View.VISIBLE
                        return false
                    }
                })
                .apply(RequestOptions()
                        .override(250, 250) // here image width and size is hardcoded but it will be shown in center fit
                        .fitCenter())
                .into(imageView)
    }
}