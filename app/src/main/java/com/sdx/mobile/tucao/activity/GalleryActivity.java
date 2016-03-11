package com.sdx.mobile.tucao.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.sdx.mobile.tucao.R;
import com.sdx.mobile.tucao.adapter.GalleryAdapter;
import com.sdx.mobile.tucao.base.BaseActivity;
import com.sdx.mobile.tucao.base.BaseToolBarActivity;
import com.sdx.mobile.tucao.util.Toaster;
import com.sdx.mobile.tucao.widget.SpaceItemDecoration;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 图库界面
 * <p/>
 * Created by yi on 2015/7/6.
 */
public class GalleryActivity extends BaseToolBarActivity {
    private int mLimit;
    private RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        Intent intent = getIntent();
        mLimit = intent.getIntExtra("", 10);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(3, 20, true));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new GalleryAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        new GetImageTask(this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_text, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_text) {
            ArrayList<String> imageList = mAdapter.getCheckList();
            if (imageList.size() == 0) {
                Toaster.show(this, R.string.string_picker_image_null_text);
                return false;
            }
            if (imageList.size() > mLimit) {
                Toaster.show(this, R.string.string_picker_image_msg_count_text, mLimit);
                return false;
            }

            Intent data = new Intent();
            data.putExtra("data", mAdapter.getCheckList());
            setResult(RESULT_OK, data);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 读取图库图片资源
     *
     * @return
     */
    private List<String> readImageMedia() {
        Cursor imageCursor = null;
        List<String> dataList = new ArrayList<String>();
        try {
            final String[] columns = {
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.ImageColumns.DATE_MODIFIED};
            final String orderBy = MediaStore.Images.Media.DATE_MODIFIED;
            imageCursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);
            while (imageCursor.moveToNext()) {
                dataList.add(imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATA)));
            }
            // 倒序排列
            Collections.reverse(dataList);
        } finally {
            if (imageCursor != null) {
                imageCursor.close();
            }
        }
        return dataList;
    }

    class GetImageTask extends AsyncTask<Void, Void, List<String>> {
        private WeakReference<Activity> mReference;

        public GetImageTask(Activity activity) {
            mReference = new WeakReference<Activity>(activity);
        }

        @Override
        protected List<String> doInBackground(Void... params) {
            return readImageMedia();
        }

        @Override
        protected void onPostExecute(List<String> dataList) {
            if (mReference.get() != null) {
                mAdapter.setDataList(dataList);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
