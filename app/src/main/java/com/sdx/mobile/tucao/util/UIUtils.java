package com.sdx.mobile.tucao.util;

import android.content.Context;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import com.sdx.mobile.tucao.app.GlobalContext;
import com.sdx.mobile.tucao.constant.Constants;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Name: Utils
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2015/12/15 14:42
 * Desc:
 */
public class UIUtils implements Constants {

    private UIUtils() {
    }

    public static String getString(Context context, int id, Object... formats) {
        Resources mResource = context.getResources();
        return mResource.getString(id, formats);
    }

    /**
     * 格式化特殊字符
     *
     * @param match
     * @param text
     * @param colorResId
     * @return
     */
    public static CharSequence formatText(CharSequence text, String match, int colorResId) {
        Resources res = GlobalContext.getInstance().getResources();
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);
        try {
            Pattern pattern = Pattern.compile(match);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                spannable.setSpan(new ForegroundColorSpan(res.getColor(colorResId)), matcher.start(),
                        matcher.end(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return spannable;
    }

    /**
     * 获取应用根目录
     *
     * @param context
     * @return
     */
    public static File getRootFile(Context context) {
        if (context == null)
            throw new NullPointerException("context is not null");

        File extCacheDir = context.getExternalCacheDir();
        if (extCacheDir == null) {
            extCacheDir = context.getCacheDir();
        }

        return extCacheDir;
    }

    /**
     * 获取应用的缓存目录
     *
     * @param context
     * @param dirPath
     * @return
     */
    public static File getBestCacheDir(Context context, String dirPath) {
        if (context == null)
            throw new NullPointerException("context is not null");

        File extCacheDir = context.getExternalCacheDir();
        if (extCacheDir == null) {
            extCacheDir = context.getCacheDir();
        }
        // 设置缓存目录
        File cacheDir = new File(extCacheDir, dirPath);
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir;
    }

    public static String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return dateFormat.format(date);
    }

    /**
     * 获取文件名称
     *
     * @param imagePath
     * @return
     */
    public static String getFileName(String imagePath) {
        int lastIndex = imagePath.lastIndexOf("/");
        return imagePath.substring(lastIndex + 1);
    }
}