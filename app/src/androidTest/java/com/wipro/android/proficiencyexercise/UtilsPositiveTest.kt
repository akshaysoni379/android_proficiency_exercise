package com.wipro.android.proficiencyexercise

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.wipro.android.proficiencyexercise.data.remote.response.CanadaList
import com.wipro.android.proficiencyexercise.utils.Utils
import com.wipro.android.proficiencyexercise.view.list.ListFragmentViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.rules.TestRule
import org.junit.Rule



@RunWith(AndroidJUnit4::class)
class UtilsPositiveTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.wipro.android.proficiencyexercise", appContext.packageName)
    }

    @Test
    fun isNetworkOn() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals(Utils.checkNetwork(appContext),true);
    }

    @Throws(InterruptedException::class)
    fun <T> LiveData<T>.getValueBlocking(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)
        val innerObserver = Observer<T> {
            value = it
            latch.countDown()
        }
        observeForever(innerObserver)
        latch.await(2, TimeUnit.SECONDS)
        return value
    }
}
