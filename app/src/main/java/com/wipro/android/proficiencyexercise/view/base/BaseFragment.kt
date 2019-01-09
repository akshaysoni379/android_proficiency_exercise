package com.wipro.android.proficiencyexercise.view.base


import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.wipro.android.proficiencyexercise.R
import com.wipro.android.proficiencyexercise.view.MainActivity

abstract class BaseFragment : Fragment() {

    protected var dialog: ProgressDialog? = null

    protected val baseActivity: MainActivity
        get() = (activity as MainActivity?)!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val textView = TextView(activity)
        dialog = ProgressDialog(activity)
        dialog!!.setMessage(getString(R.string.please_wait))
        dialog!!.setCancelable(false)
        return textView
    }
}
