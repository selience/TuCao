package com.sdx.mobile.tucao.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Name: EncryptUtils
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/7 15:04
 * Desc:
 */
public class EncryptUtils {
    private EncryptUtils() {
    }

    public static String md5ForString(String key) {
        String cacheKey;
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(key.getBytes());
            cacheKey = bytesToMd5String(e.digest());
        } catch (NoSuchAlgorithmException var3) {
            cacheKey = String.valueOf(key.hashCode());
        }

        return cacheKey;
    }

    private static String bytesToMd5String(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < bytes.length; ++i) {
            int b = bytes[i] & 255;
            if(b < 16) {
                sb.append('0');
            }

            sb.append(Integer.toHexString(b));
        }

        return sb.toString();
    }
}
