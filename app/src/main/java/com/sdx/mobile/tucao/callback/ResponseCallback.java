package com.sdx.mobile.tucao.callback;

import com.sdx.mobile.tucao.model.Result;
import java.lang.ref.WeakReference;
import rx.Observer;

/**
 * Name: ResponseCallback
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/8 18:00
 * Desc:
 */
public class ResponseCallback implements Observer<Result> {
    private String mTaskName;
    private WeakReference<TaskListener> mWeakReference;

    public ResponseCallback(String taskName, TaskListener taskListener) {
        mTaskName = taskName;
        mWeakReference = new WeakReference<TaskListener>(taskListener);
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        TaskListener taskListener = mWeakReference.get();
        if (taskListener != null) {
            taskListener.onFailure(mTaskName, e);
        }
    }

    @Override
    public void onNext(Result result) {
        TaskListener taskListener = mWeakReference.get();
        if (taskListener != null) {
            taskListener.onSuccess(mTaskName, result);
        }
    }
}
