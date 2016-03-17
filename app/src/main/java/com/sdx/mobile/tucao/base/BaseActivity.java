package com.sdx.mobile.tucao.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.sdx.mobile.tucao.api.APIService;
import com.sdx.mobile.tucao.app.ApplicationModule;
import com.sdx.mobile.tucao.callback.ResponseCallback;
import com.sdx.mobile.tucao.callback.TaskListener;
import com.sdx.mobile.tucao.constant.Constants;
import com.sdx.mobile.tucao.model.HttpResult;
import com.umeng.analytics.MobclickAgent;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Name: BaseActivity
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/7 15:45
 * Desc:
 */
public class BaseActivity extends AppCompatActivity implements TaskListener, Constants {
    protected APIService mService;
    protected ApplicationModule mModule;
    private CompositeSubscription mSubscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubscriptions = new CompositeSubscription();
        mModule = ApplicationModule.getInstance();
        mService = mModule.create(APIService.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        mSubscriptions.unsubscribe();
        super.onDestroy();
    }

    protected void executeTask(Observable observable, String taskName) {
        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseCallback(taskName, this));
        addSubscription(subscription);
    }

    public void addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }


    @Override
    public void onSuccess(String taskName, HttpResult response) {
    }

    @Override
    public void onFailure(String taskName, Throwable ex) {
    }
}
