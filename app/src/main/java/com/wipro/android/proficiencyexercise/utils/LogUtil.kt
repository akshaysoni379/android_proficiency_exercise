package com.wipro.android.proficiencyexercise.utils

import android.util.Log

import com.wipro.android.proficiencyexercise.BuildConfig

object LogUtil {
    private const val LOG_LEVEL_VERBOSE = 1
    private const val LOG_LEVEL_DEBUG = 2
    private const val LOG_LEVEL_INFO = 3
    private const val LOG_LEVEL_WARN = 4
    private const val LOG_LEVEL_ERROR = 5

    fun e(tag: String, msg: String) {
        log(LOG_LEVEL_ERROR, tag, msg)
    }

    fun d(tag: String, msg: String) {
        log(LOG_LEVEL_DEBUG, tag, msg)
    }

    private fun log(level: Int, tag: String, msg: String) {
        var msg1 = msg
        if (BuildConfig.DEBUG) {
            val currentTime = System.currentTimeMillis()
            msg1 = "[Time:$currentTime] $msg1"
            when (level) {
                LOG_LEVEL_DEBUG -> Log.d(tag, msg1)
                LOG_LEVEL_ERROR -> Log.e(tag, msg1)
                LOG_LEVEL_INFO -> Log.i(tag, msg1)
                LOG_LEVEL_WARN -> Log.w(tag, msg1)
                LOG_LEVEL_VERBOSE -> Log.v(tag, msg1)
                else -> Log.v(tag, msg1)
            }
        }
    }
}
