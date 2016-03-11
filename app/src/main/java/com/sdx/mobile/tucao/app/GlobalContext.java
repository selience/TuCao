package com.sdx.mobile.tucao.app;

import android.app.Application;
import android.text.TextUtils;

import com.sdx.mobile.tucao.BuildConfig;
import com.sdx.mobile.tucao.model.UserModel;
import com.sdx.mobile.tucao.util.DebugLog;
import com.sdx.mobile.tucao.util.PreferenceUtils;

/**
 * Name: GlobalContext
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/7 15:05
 * Desc:
 */
public class GlobalContext extends Application {

    private UserModel mUserModel;
    private static GlobalContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        mUserModel = PreferenceUtils.getUser(this);
        DebugLog.enableDebugLogging(BuildConfig.DEBUG);
    }

    public boolean isLogin() {
        return (mUserModel != null && !TextUtils.isEmpty(mUserModel.getAuth()));
    }

    public UserModel getmUserModel() {
        return this.mUserModel;
    }

    public String getUserAuth() {
        return (isLogin() ? mUserModel.getAuth() : "");
    }

    public void setmUserModel(UserModel userBean) {
        this.mUserModel = userBean;
        PreferenceUtils.storeUser(this, userBean);
    }

    public static GlobalContext getInstance() {
        return instance;
    }
}
