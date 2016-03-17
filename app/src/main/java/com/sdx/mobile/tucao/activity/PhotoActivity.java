package com.sdx.mobile.tucao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import com.sdx.mobile.tucao.R;
import com.sdx.mobile.tucao.base.BaseToolBarActivity;
import com.sdx.mobile.tucao.fragment.ImageFragment;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoActivity extends BaseToolBarActivity {

    @Bind(R.id.id_viewpager)
    ViewPager mViewPager;

    private int mPosition;
    private ArrayList<String> mImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mPosition = intent.getIntExtra(EXTRA_IMAGE_POSTION, 0);
        mImageList = (ArrayList<String>) intent.getSerializableExtra(EXTRA_IMAGE_LIST);

        mViewPager.setAdapter(new ImageAdapter(getSupportFragmentManager(), mImageList));
        mViewPager.setCurrentItem(mPosition);
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mPosition = position;
                setTitle((position + 1) + "/" + mImageList.size());
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        setTitle((mPosition + 1) + "/" + mImageList.size());
    }

    static class ImageAdapter extends FragmentStatePagerAdapter {
        private List<String> imageList;

        public ImageAdapter(FragmentManager fm, List<String> imageList) {
            super(fm);
            this.imageList = imageList;
        }

        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return ImageFragment.newInstance(imageList.get(position));
        }
    }

}
