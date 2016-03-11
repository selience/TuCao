package com.sdx.mobile.tucao.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Name: Toaster
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/7 17:22
 * Desc:
 */
public class Toaster {
    private static Toast toast;
    private static Handler handler = new Handler(Looper.getMainLooper());

    public Toaster() {
    }

    public static void show(Context context, int resId) {
        if (context != null) {
            show(context, context.getResources().getText(resId), 0);
        }
    }

    public static void show(Context context, int resId, int duration) {
        if (context != null) {
            show(context, context.getResources().getText(resId), duration);
        }
    }

    public static void show(Context context, CharSequence text) {
        show(context, text, 0);
    }

    public static void show(Context context, int resId, Object... args) {
        if (context != null) {
            show(context, context.getResources().getString(resId), args);
        }
    }

    public static void show(Context context, String format, Object... args) {
        show(context, String.format(format, args), 0);
    }

    public static void show(Context context, int resId, int duration, Object... args) {
        if (context != null) {
            show(context, context.getResources().getString(resId), duration, args);
        }
    }

    public static void show(Context context, String format, int duration, Object... args) {
        show(context, String.format(format, args), duration);
    }

    public static void show(Context context, final CharSequence text, final int duration) {
        if (context != null) {
            final Context appContext = context.getApplicationContext();
            handler.post(new Runnable() {
                public void run() {
                    if (Toaster.toast != null) {
                        Toaster.toast.setText(text);
                        Toaster.toast.setDuration(duration);
                        Toaster.toast.show();
                    } else {
                        Toaster.toast = Toast.makeText(appContext, text, duration);
                        Toaster.toast.show();
                    }

                }
            });
        }
    }
}
