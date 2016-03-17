package com.sdx.mobile.tucao.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Name: HttpResult
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/17 13:12
 * Desc:
 */
public class HttpResult<T> {
    private int code;
    private String msg;
    private T data;
    private int max_id;
    private int is_end;

    public boolean isOk() {
        return (code == 0);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getMax_id() {
        return max_id;
    }

    public void setMax_id(int max_id) {
        this.max_id = max_id;
    }

    public boolean isEnd() {
        return (is_end == 1);
    }

    public void setIs_end(int is_end) {
        this.is_end = is_end;
    }

    public String getImageUrl() {
        if (data != null && data.toString().length() > 0) {
            JSONObject json = JSON.parseObject(data.toString());
            return json.getString("imgurl");
        }
        return "";
    }
}
