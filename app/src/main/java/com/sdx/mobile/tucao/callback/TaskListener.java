package com.sdx.mobile.tucao.callback;

import com.sdx.mobile.tucao.model.Result;

/**
 * Name: TaskListener
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2015/12/15 17:25
 * Desc:
 */
public interface TaskListener {

    void onSuccess(String taskName, Result result);

    void onFailure(String taskName, Throwable ex);
}
