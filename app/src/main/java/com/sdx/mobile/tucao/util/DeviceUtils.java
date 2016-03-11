package com.sdx.mobile.tucao.util;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Name: DeviceUtils
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/7 19:12
 * Desc:
 */
public class DeviceUtils {
    private DeviceUtils() {
    }

    public static float dp2px(Context context, float value) {
        return TypedValue.applyDimension(1, value, getMetrics(context));
    }

    public static float px2dp(Context context, float pxValue) {
        float scale = getMetrics(context).density;
        return pxValue / scale + 0.5F;
    }

    public static float sp2px(Context context, float value) {
        return TypedValue.applyDimension(2, value, getMetrics(context));
    }

    public static float px2sp(Context context, float value) {
        float scaledDensity = getMetrics(context).scaledDensity;
        return value / scaledDensity + 0.5F;
    }

    public static DisplayMetrics getMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    public static float getScreenWidth(Context context) {
        return (float) getMetrics(context).widthPixels;
    }

    public static float getScreenHeight(Context context) {
        return (float) getMetrics(context).heightPixels;
    }

    public static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (androidId == null) {
            androidId = Settings.System.getString(context.getContentResolver(), "android_id");
        }

        if (androidId == null) {
            androidId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        }

        return androidId;
    }
}
