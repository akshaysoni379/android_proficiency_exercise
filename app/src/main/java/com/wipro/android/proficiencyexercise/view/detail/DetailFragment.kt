package com.wipro.android.proficiencyexercise.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.wipro.android.proficiencyexercise.R
import com.wipro.android.proficiencyexercise.data.remote.response.Row
import com.wipro.android.proficiencyexercise.view.base.BaseFragment

class DetailFragment : BaseFragment() {

    private var desc: TextView? = null
    private var image: ImageView? = null
    private var rows: Row? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rows = (if (arguments != null) arguments!!.getSerializable("rows") else null) as Row
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.detail_fragment, container, false)
        desc = view.findViewById(R.id.desc)
        image = view.findViewById(R.id.image)
        setViewData()
        return view
    }

    private fun setViewData() {
        if (rows != null) {
            if (rows!!.title != null && !rows!!.title!!.isEmpty())
                if (baseActivity.supportActionBar != null) {
                    baseActivity.supportActionBar!!.title = rows!!.title
                }
            if (rows!!.description != null && !rows!!.description!!.isEmpty())
                desc!!.text = rows!!.description
            if (rows!!.imageHref != null && !rows!!.imageHref!!.isEmpty())
                Glide.with(baseActivity)
                        .load(rows!!.imageHref)
                        .into(image!!)
        }
    }

    companion object {

        fun newInstance(rows: Row): DetailFragment {
            val args = Bundle()
            args.putSerializable("rows", rows)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}