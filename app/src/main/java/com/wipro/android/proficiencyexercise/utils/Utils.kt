package com.wipro.android.proficiencyexercise.utils

import android.content.Context
import android.net.ConnectivityManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

object Utils {

    fun checkNetwork(context: Context?): Boolean {
        if (context == null) {
            return true
        }
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                true
            } else {
                activeNetwork.type == ConnectivityManager.TYPE_MOBILE
            }
        } else {
            false
        }
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
