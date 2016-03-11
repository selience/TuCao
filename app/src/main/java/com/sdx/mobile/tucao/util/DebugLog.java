package com.sdx.mobile.tucao.util;

import android.util.Log;
import java.util.Locale;

/**
 * Name: DebugLog
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/7 15:05
 * Desc:
 */
public class DebugLog {
    private static boolean DEBUG = false;

    public DebugLog() {
    }

    public static void enableDebugLogging(boolean enabled) {
        DEBUG = enabled;
    }

    public static boolean isDebug() {
        return DEBUG;
    }

    public static void v(String msgFormat) {
        log(2, (String)null, msgFormat, (Throwable)null);
    }

    public static void v(String tag, String msgFormat) {
        log(2, tag, msgFormat, (Throwable)null);
    }

    public static void v(String tag, String msgFormat, Throwable t) {
        log(2, tag, msgFormat, t);
    }

    public static void d(String msgFormat) {
        log(3, (String)null, msgFormat, (Throwable)null);
    }

    public static void d(String tag, String msgFormat) {
        log(3, tag, msgFormat, (Throwable)null);
    }

    public static void d(String tag, String msgFormat, Throwable t) {
        log(3, tag, msgFormat, t);
    }

    public static void i(String msgFormat) {
        log(4, (String)null, msgFormat, (Throwable)null);
    }

    public static void i(String tag, String msgFormat) {
        log(4, tag, msgFormat, (Throwable)null);
    }

    public static void i(String tag, String msgFormat, Throwable t) {
        log(4, tag, msgFormat, t);
    }

    public static void w(String msgFormat) {
        log(5, (String)null, msgFormat, (Throwable)null);
    }

    public static void w(String tag, String msgFormat) {
        log(5, tag, msgFormat, (Throwable)null);
    }

    public static void w(String tag, String msgFormat, Throwable t) {
        log(5, tag, msgFormat, t);
    }

    public static void e(String msgFormat) {
        log(6, (String)null, msgFormat, (Throwable)null);
    }

    public static void e(String tag, String msgFormat) {
        log(6, tag, msgFormat, (Throwable)null);
    }

    public static void e(String tag, String msgFormat, Throwable t) {
        log(6, tag, msgFormat, t);
    }

    private static void log(int level, String s1, String msgFormat, Throwable t) {
        if(DEBUG) {
            Thread thread = Thread.currentThread();
            String msg = msgFormat == null?"":msgFormat;
            StackTraceElement stackTraceElement = thread.getStackTrace()[4];
            String fullClassName = stackTraceElement.getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String tag = s1 == null?className:s1;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(stackTraceElement.getMethodName());
            stringBuilder.append("@");
            stringBuilder.append(stackTraceElement.getLineNumber());
            stringBuilder.append(">>>");
            stringBuilder.append(String.format(Locale.US, "[%d:%s] %s", new Object[]{Long.valueOf(thread.getId()), thread.getName(), msg}));
            switch(level) {
                case 2:
                    if(t != null) {
                        Log.v(tag, stringBuilder.toString(), t);
                    } else {
                        Log.v(tag, stringBuilder.toString());
                    }
                    break;
                case 3:
                    if(t != null) {
                        Log.d(tag, stringBuilder.toString(), t);
                    } else {
                        Log.d(tag, stringBuilder.toString());
                    }
                    break;
                case 4:
                    if(t != null) {
                        Log.i(tag, stringBuilder.toString(), t);
                    } else {
                        Log.i(tag, stringBuilder.toString());
                    }
                    break;
                case 5:
                    if(t != null) {
                        Log.w(tag, stringBuilder.toString(), t);
                    } else {
                        Log.w(tag, stringBuilder.toString());
                    }
                    break;
                case 6:
                    if(t != null) {
                        Log.e(tag, stringBuilder.toString(), t);
                    } else {
                        Log.e(tag, stringBuilder.toString());
                    }
            }

        }
    }
}
