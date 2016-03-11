package com.sdx.mobile.tucao.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * Name: Result
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2015/12/16 18:20
 * Desc:
 */
public class Result {

    private int code;
    private String msg;
    private String data;
    private int max_id;
    private int is_end;
    private Object value;

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Object getValue() {
        return value;
    }

    public Result setValue(Object value) {
        this.value = value;
        return this;
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
        if (data != null && data.length() > 0) {
            JSONObject json = JSON.parseObject(data);
            return json.getString("imgurl");
        }
        return "";
    }

    public static <T> Object parse(String dataString, Class<T> clazz) {
        if (clazz == null) return null;

        try {
            if (dataString.startsWith("{")) {
                return JSON.parseObject(dataString, clazz);
            } else {
                return JSON.parseArray(dataString, clazz);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void updateValue(Class clazz) {
        if (clazz == null) return;

        try {
            if (data.startsWith("{")) {
                setValue(JSON.parseObject(data, clazz));
            } else {
                setValue(JSON.parseArray(data, clazz));
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}
