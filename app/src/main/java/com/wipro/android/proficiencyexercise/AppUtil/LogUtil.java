package com.wipro.android.proficiencyexercise.AppUtil;

import android.util.Log;

import com.wipro.android.proficiencyexercise.BuildConfig;

public class LogUtil {
    private static final int LOG_LEVEL_VERBOSE = 1;
    private static final int LOG_LEVEL_DEBUG = 2;
    private static final int LOG_LEVEL_INFO = 3;
    private static final int LOG_LEVEL_WARN = 4;
    private static final int LOG_LEVEL_ERROR = 5;


    public static void e(String tag, String msg) {
        log(LOG_LEVEL_ERROR, tag, msg);
    }

    public static void w(String tag, String msg) {
        log(LOG_LEVEL_WARN, tag, msg);
    }

    public static void d(String tag, String msg) {
        log(LOG_LEVEL_DEBUG, tag, msg);
    }

    public static void i(String tag, String msg) {
        log(LOG_LEVEL_INFO, tag, msg);
    }

    public static void v(String tag, String msg) {
        log(LOG_LEVEL_VERBOSE, tag, msg);
    }

    private static void log(int level, String tag, String msg) {
        if (BuildConfig.DEBUG) {
            long currentTime = System.currentTimeMillis();
            msg = "[Time:" + currentTime + "] " + msg;
            switch (level) {
                case LOG_LEVEL_DEBUG:
                    Log.d(tag, msg);
                    break;
                case LOG_LEVEL_ERROR:
                    Log.e(tag, msg);
                    break;
                case LOG_LEVEL_INFO:
                    Log.i(tag, msg);
                    break;
                case LOG_LEVEL_WARN:
                    Log.w(tag, msg);
                    break;
                case LOG_LEVEL_VERBOSE:
                    Log.v(tag, msg);
                    break;
                default:
                    Log.v(tag, msg);
            }
        }
    }
}
