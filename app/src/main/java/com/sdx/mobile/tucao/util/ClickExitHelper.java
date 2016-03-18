package com.sdx.mobile.tucao.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.sdx.mobile.tucao.R;

public class ClickExitHelper {
    private Context context;
    private Handler mHandler;
    private Toast mBackToast;
    private boolean isOnKeyBacking;

    public ClickExitHelper(Context context) {
        this.context = context;
        mHandler = new Handler(Looper.getMainLooper());
    }

    public boolean onBackPressed() {
        if (isOnKeyBacking) {
            mHandler.removeCallbacks(onBackTimeRunnable);
            if (mBackToast != null) {
                mBackToast.cancel();
            }
            return true;
        } else {
            isOnKeyBacking = true;
            if (mBackToast == null) {
                mBackToast = Toast.makeText(context, R.string.string_back_exit_text,
                        Toast.LENGTH_SHORT);
            }
            mBackToast.show();
            mHandler.postDelayed(onBackTimeRunnable, 2000);
            return false;
        }
    }

    private Runnable onBackTimeRunnable = new Runnable() {

        @Override
        public void run() {
            isOnKeyBacking = false;
            if (mBackToast != null) {
                mBackToast.cancel();
            }
        }
    };
}

