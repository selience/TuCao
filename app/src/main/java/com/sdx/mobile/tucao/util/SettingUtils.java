package com.sdx.mobile.tucao.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Name: SettingUtils
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/21 14:02
 * Desc:
 */
public class SettingUtils {

    private static final String PREFERENCE_NAME = "preference";

    private static final String PREFERENCE_FIRST = "isFirst";

    public static void storeFirstStatus(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PREFERENCE_FIRST, true);
        editor.apply();
    }

    public static boolean isFirstStatus(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(
                PREFERENCE_NAME, context.MODE_PRIVATE);
        return preferences.getBoolean(PREFERENCE_FIRST, false);
    }
}
