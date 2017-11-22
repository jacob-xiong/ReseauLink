package com.reseau.link.utils;

import android.util.Log;

/**
 * @author by xiongyan on 2017/11/20.
 */

public class MyLog {
    private MyLog() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 是否需要打印bug
     */
    public static boolean isDebug = true;
    private static final String TAG = "jacob";

    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }

    /**
     * 传入自定义tag
     */
    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

}
