package com.sdx.mobile.tucao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import com.sdx.mobile.tucao.R;
import com.sdx.mobile.tucao.base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Name: activity_splash
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/17 15:25
 * Desc:
 */
public class SplashActivity extends BaseActivity implements Runnable {

    @Bind(R.id.id_splash_view)
    ImageView mSplashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        mSplashView.postDelayed(this, 2000L);
    }

    @Override
    public void run() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
