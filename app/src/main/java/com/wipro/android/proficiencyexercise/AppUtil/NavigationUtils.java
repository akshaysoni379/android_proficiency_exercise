package com.wipro.android.proficiencyexercise.AppUtil;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class NavigationUtils {

    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int container) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment previousFragment = fragmentManager.findFragmentById(container);

        /**
         * Hide previous fragment to remove focus from there
         */
        if (previousFragment != null) {
            ft.hide(previousFragment);
        }

        ft.show(fragment).add(container, fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }

    public static void clearAllFragment(FragmentManager fragmentManager) {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
