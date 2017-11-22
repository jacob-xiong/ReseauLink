package com.reseau.link.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;

/**
 * @author by xiongyan on 2017/11/20.
 */

public class MyApplication extends MultiDexApplication {
    private static String packName = "link.reseau.com.reseaulink";

    @Override
    public void onCreate() {
        super.onCreate();
        initConfig();
    }

    private void initConfig() {
        Config.VERSION_CODE = String.valueOf(getVersionCode(this));
        Config.VERSION_NAME = String.valueOf(getVersionCode(this));
        Config.SCREEN_HEIGHT = ScreenUtils.getScreenHeight(this);
        Config.SCREEN_WIDTH = ScreenUtils.getScreenWidth(this);
    }

    /**
     *    获取软件版本号，
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {

            versionCode = context.getPackageManager().getPackageInfo(packName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     *  获取软件版本名称，
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = null;
        try {

            versionName = context.getPackageManager().getPackageInfo(packName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
