package com.wipro.android.proficiencyexercise.view.base


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wipro.android.proficiencyexercise.view.MainActivity

abstract class BaseFragment : Fragment() {

    protected val baseActivity: MainActivity
        get() = (activity as MainActivity?)!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return TextView(activity)
    }
}
