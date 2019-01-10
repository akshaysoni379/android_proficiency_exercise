package com.wipro.android.proficiencyexercise.view

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.wipro.android.proficiencyexercise.R
import com.wipro.android.proficiencyexercise.utils.Utils
import com.wipro.android.proficiencyexercise.view.base.BaseActivity
import com.wipro.android.proficiencyexercise.view.list.ListFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)

        Utils.addFragment(supportFragmentManager,
                ListFragment(),
                R.id.fragment_container)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            moveTaskToBack(true)
        } else {
            super.onBackPressed()
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}