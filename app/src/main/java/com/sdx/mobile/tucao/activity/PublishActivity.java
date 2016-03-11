package com.sdx.mobile.tucao.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sdx.mobile.tucao.R;
import com.sdx.mobile.tucao.app.GlobalContext;
import com.sdx.mobile.tucao.base.BaseToolBarActivity;
import com.sdx.mobile.tucao.model.RequestParams;
import com.sdx.mobile.tucao.model.Result;
import com.sdx.mobile.tucao.util.DebugLog;
import com.sdx.mobile.tucao.util.Toaster;
import com.sdx.mobile.tucao.widget.ExtendMediaPicker;
import com.sdx.mobile.tucao.widget.UIPickImageView;

import org.json.JSONArray;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Name: PublishActivity
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/9 13:12
 * Desc:
 */
public class PublishActivity extends BaseToolBarActivity implements ExtendMediaPicker.OnMediaPickerListener {
    private static final String PUBLISH_TOPIC_TASK = "PUBLISH_TOPIC_TASK";
    private static final String START_PHOTO_UPLOAD_TASK = "START_PHOTO_UPLOAD_TASK";

    @Bind(R.id.id_post_title)
    TextView mPostTitleView;
    @Bind(R.id.id_post_content)
    EditText mPostContentView;
    @Bind(R.id.id_post_pickview)
    UIPickImageView mPickImageView;

    private String mSubject;
    private Set<String> mUploadList;
    private ExtendMediaPicker mMediaPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        ButterKnife.bind(this);
        setBackgroundColor(Color.WHITE);

        Intent intent = getIntent();
        mSubject = intent.getStringExtra(INTENT_TOPIC_SUBJECT);
        mPostTitleView.setText(mSubject);

        mUploadList = new HashSet<>();
        mMediaPicker = new ExtendMediaPicker(this, 9);
        mMediaPicker.setOnMediaPickerListener(this);
        mPickImageView.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPicker.showPickerView();
            }
        });
    }

    private void uploadImage() {
        final RequestParams params = new RequestParams();
        params.addParam("type", "tucao");

        Observable observable = Observable.from(mPickImageView.getImageList())
                .flatMap(new Func1<String, Observable<Result>>() {
                    @Override
                    public Observable<Result> call(String s) {
                        RequestBody fileBody = RequestBody.create(
                                MediaType.parse("multipart/form-data"), new File(s));
                        return mService.upload(fileBody, params.query());
                    }
                });
        execute(observable, null, START_PHOTO_UPLOAD_TASK);
    }

    private void publishTopic() {
        if (TextUtils.isEmpty(mPostContentView.getText())) {
            Toaster.show(this, R.string.string_publish_content_text);
            return;
        }

        // 吐槽照片
        JSONArray jsonArray = new JSONArray();
        if (mUploadList.size() > 0) {
            jsonArray = new JSONArray(mUploadList);
        }

        RequestParams params = new RequestParams();
        params.addField("subject", mSubject);
        params.addField("text", mPostContentView.getText().toString());
        params.addField("imgs", jsonArray.toString());
        params.addParam("auth", GlobalContext.getInstance().getUserAuth());
        execute(mService.publishTopic(params.fields(), params.query()), null, PUBLISH_TOPIC_TASK);
    }

    @Override
    public void onSuccess(String taskName, Result result) {
        if (result.isOk()) {
            if (taskName.equals(START_PHOTO_UPLOAD_TASK)) {
                // 处理上传图片
                mUploadList.add(result.getImageUrl());
            } else if (taskName.equals(PUBLISH_TOPIC_TASK)) {
                Toaster.show(this, R.string.string_publish_content_success);
                finish();
            }
        } else {
            Toaster.show(this, result.getMsg());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.id_menu_post) {
            publishTopic();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSingleMediaChanged(String imagePath) {
        mPickImageView.addItemView(imagePath);
    }

    @Override
    public void onMultipleMediaChanged(List<String> imageList) {
        mPickImageView.addItemView(imageList);

        uploadImage();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mMediaPicker.onActivityResult(requestCode, resultCode, data);
    }
}
