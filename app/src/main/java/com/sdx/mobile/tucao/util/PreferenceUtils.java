package com.sdx.mobile.tucao.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.sdx.mobile.tucao.model.UserModel;

/**
 * Name: PreferenceUtils
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2015/12/22 18:03
 * Desc:
 */
public class PreferenceUtils {
    private static final String PREFERENCE_USER_KEY = "user";

    private static final String PREFERENCE_USER_AUTH = "auth";
    private static final String PREFERENCE_USER_NICKNAME = "nick_name";
    private static final String PREFERENCE_USER_PHONE = "phone";
    private static final String PREFERENCE_USER_AVATAR = "user_face";

    private static SharedPreferences getUserPreference(Context context) {
        return context.getSharedPreferences(PREFERENCE_USER_KEY, Context.MODE_PRIVATE);
    }

    public static void storeUser(Context context, UserModel user) {
        SharedPreferences prefs = getUserPreference(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFERENCE_USER_AUTH, user.getAuth());
        editor.putString(PREFERENCE_USER_NICKNAME, user.getNick_name());
        editor.putString(PREFERENCE_USER_PHONE, user.getPhone());
        editor.putString(PREFERENCE_USER_AVATAR, user.getUser_face());
        editor.apply();
    }

    public static UserModel getUser(Context context) {
        SharedPreferences prefs = getUserPreference(context);

        UserModel user = new UserModel();
        user.setAuth(prefs.getString(PREFERENCE_USER_AUTH, ""));
        user.setNick_name(prefs.getString(PREFERENCE_USER_NICKNAME, ""));
        user.setPhone(prefs.getString(PREFERENCE_USER_PHONE, ""));
        user.setUser_face(prefs.getString(PREFERENCE_USER_AVATAR, ""));
        return user;
    }

    public static void clearUser(Context context) {
        SharedPreferences prefs = getUserPreference(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear().apply();
    }
}
