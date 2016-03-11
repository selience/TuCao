package com.sdx.mobile.tucao.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sdx.mobile.tucao.api.APIService;
import com.sdx.mobile.tucao.app.ApplicationModule;
import com.sdx.mobile.tucao.callback.ResponseCallback;
import com.sdx.mobile.tucao.callback.TaskListener;
import com.sdx.mobile.tucao.constant.Constants;
import com.sdx.mobile.tucao.model.Result;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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
    protected void onDestroy() {
        mSubscriptions.unsubscribe();
        super.onDestroy();
    }

   /* protected void execute(Observable observable, String taskName) {
        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseCallback(taskName, this));
        addSubscription(subscription);
    }*/

    protected void execute(Observable observable, final Class clazz, String taskName) {
        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Result, Result>() {
                    @Override
                    public Result call(Result result) {
                        return result.setValue(Result.parse(result.getData(), clazz));
                    }
                })
                .subscribe(new ResponseCallback(taskName, this));
        addSubscription(subscription);
    }

    public void addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }


    @Override
    public void onSuccess(String taskName, Result response) {
    }

    @Override
    public void onFailure(String taskName, Throwable ex) {
    }
}
