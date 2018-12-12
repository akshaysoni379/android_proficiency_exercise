package com.wipro.android.proficiencyexercise.AppUtil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class Utils {

    public static boolean checkNetwork(Context context) {
        if (context == null) {
            return true;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            } else {
                return activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
            }
        } else {
            return false;
        }
    }

    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int container) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment previousFragment = fragmentManager.findFragmentById(container);

        if (previousFragment != null) {
            ft.hide(previousFragment);
        }

        ft.show(fragment).add(container, fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }
}
