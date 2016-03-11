package com.sdx.mobile.tucao.base;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sdx.mobile.tucao.R;

/**
 * Name: BaseActivity
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2015/12/4 16:20
 * Desc: Activity基类
 */
public class BaseToolBarActivity extends BaseActivity {

    private Toolbar mToolBar;
    private TextView mTitleView;
    private LinearLayout mRootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        mToolBar = (Toolbar) findViewById(R.id.id_toolbar);
        mTitleView = (TextView) findViewById(R.id.id_toolbar_text);
        mRootLayout = (LinearLayout) findViewById(R.id.id_content_base);

        if (mToolBar != null) {
            mToolBar.setTitle("");
            setSupportActionBar(mToolBar);
        }

        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null && enableDefaultBack()) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        }
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        mTitleView.setText(title);
        super.onTitleChanged(title, color);
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, mRootLayout, true);
    }

    public void setBackgroundColor(int color) {
        mRootLayout.setBackgroundColor(color);
    }

    protected boolean enableDefaultBack() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
