package com.sdx.mobile.tucao.callback;

import com.sdx.mobile.tucao.model.HttpResult;

/**
 * Name: TaskListener
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2015/12/15 17:25
 * Desc:
 */
public interface TaskListener {

    void onSuccess(String taskName, HttpResult result);

    void onFailure(String taskName, Throwable ex);
}
