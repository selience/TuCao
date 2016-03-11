package com.sdx.mobile.tucao.model;

import com.sdx.mobile.tucao.util.DebugLog;
import com.sdx.mobile.tucao.util.EncryptUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Name: RequestParams
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2015/12/15 16:22
 * Desc: 请求参数处理
 */
public class RequestParams {
    private static final String PARAM_F = "F";
    private static final String PARAM_V = "V";
    private static final String PARAM_SIGN = "sign";
    private static final String PARAM_NONCESTR = "noncestr";
    private static final String PARAM_KEY = "key";

    private Map<String, String> fields; // POST请求参数
    private Map<String, String> params; // GET请求参数
    private Map<String, String> arguments; // 临时参数

    public RequestParams() {
        fields = new HashMap<String, String>();
        params = new HashMap<String, String>();
        arguments = new HashMap<String, String>();

        addParam(PARAM_F, "android");
        addParam(PARAM_V, "1.0.0");
        addParam(PARAM_NONCESTR, System.currentTimeMillis() + "");
    }

    /**
     * 添加POST参数
     *
     * @param name
     * @param value
     */
    public void addField(String name, String value) {
        this.fields.put(name, value);
        this.arguments.put(name, value);
    }

    /**
     * 添加GET参数
     *
     * @param name
     * @param value
     */
    public void addParam(String name, String value) {
        this.params.put(name, value);
        this.arguments.put(name, value);
    }

    /**
     * 生成签名参数
     *
     * @return
     */
    private String generateSign() {
        // 将所有传来的参数按照key的assii码 从小到大排序
        List<String> keys = new ArrayList<>(arguments.keySet());
        Collections.sort(keys);
        // 将所有参数连接成字符串
        StringBuilder builder = new StringBuilder();
        for (String name : keys) {
            builder.append(name);
            builder.append("=");
            builder.append(arguments.get(name));
            builder.append("&");
        }

        // 追加约定密钥
        builder.append(PARAM_KEY);
        builder.append("=");
        builder.append("9c80774c32ba7aa9d47c2a5e5d896e28");

        // 签名字符串进行MD5加密
        String md5String = EncryptUtils.md5ForString(builder.toString());
        String signString = md5String.substring(3, 27).toUpperCase();
        DebugLog.d("signString", builder.toString() + " -> " + signString);

        return signString;
    }


    public Map<String, String> query() {
        addParam(PARAM_SIGN, generateSign());
        return this.params;
    }

    public Map<String, String> fields() {
        return this.fields;
    }
}
