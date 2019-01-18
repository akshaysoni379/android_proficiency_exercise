package com.wipro.android.proficiencyexercise.utils

import android.content.Context
import android.net.ConnectivityManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

object Utils {

    fun checkNetwork(context:Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun addFragment(fragmentManager: FragmentManager, fragment: Fragment, container: Int) {
        val ft = fragmentManager.beginTransaction()
        val previousFragment = fragmentManager.findFragmentById(container)

        if (previousFragment != null) {
            ft.hide(previousFragment)
        }

        ft.show(fragment).add(container, fragment)
                .addToBackStack(fragment.javaClass.name)
                .commit()
    }
}
