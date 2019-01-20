package com.wipro.android.proficiencyexercise

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.wipro.android.proficiencyexercise.utils.Utils

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class UtilsNegativeTest {

    @Test
    fun isNetworkOff() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals(Utils.checkNetwork(appContext), false);
    }
}
