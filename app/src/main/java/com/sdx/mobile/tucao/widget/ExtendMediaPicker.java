package com.sdx.mobile.tucao.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import com.sdx.mobile.tucao.R;
import com.sdx.mobile.tucao.activity.GalleryActivity;
import com.sdx.mobile.tucao.constant.Constants;
import com.sdx.mobile.tucao.constant.IntentConstants;
import com.sdx.mobile.tucao.util.DebugLog;
import com.sdx.mobile.tucao.util.MediaUtils;
import com.sdx.mobile.tucao.util.UIUtils;
import java.io.File;
import java.util.List;

public class ExtendMediaPicker {
    private static final String TAG = ExtendMediaPicker.class.getSimpleName();

    private static final int REQUEST_CODE_PICK_IMAGE = 2001;
    private static final int REQUEST_CODE_TAKE_PHOTO = 2002;
    private static final int REQUEST_CODE_GALLERY_PHOTO = 2003;

    private int mLimit;
    private File mTakeFile;
    private Activity mActivity;
    private OnMediaPickerListener mMediaPickerListener;

    public ExtendMediaPicker(Activity activity, int limit) {
        this.mLimit = limit;
        this.mActivity = activity;
    }

    public void showPickerView() {
        // 选择图片提示
        String[] items = mActivity.getResources().getStringArray(R.array.string_pick_image_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setAdapter(new ArrayAdapter<String>(mActivity,
                android.R.layout.simple_list_item_1, items), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    openSystemCamera();
                } else {
                    startGalleryAction();
                }
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            DebugLog.e(TAG, "requestCode = " + requestCode);
            DebugLog.e(TAG, "resultCode = " + resultCode);
            DebugLog.e(TAG, "data = " + data);
            return;
        }

        switch (requestCode) {
            case REQUEST_CODE_PICK_IMAGE:
                String imagePath = MediaUtils.getPath(mActivity, data.getData());
                DebugLog.d(TAG, "CHOOSE_PICTURE: uri = " + imagePath);
                if (mMediaPickerListener != null) {
                    mMediaPickerListener.onSingleMediaChanged(imagePath);
                }
                break;
            case REQUEST_CODE_TAKE_PHOTO:
                String filePath = mTakeFile.getAbsolutePath();
                DebugLog.d(TAG, "TAKE_PICTURE: uri = " + filePath);
                if (mMediaPickerListener != null) {
                    mMediaPickerListener.onSingleMediaChanged(filePath);
                }
                break;
            case REQUEST_CODE_GALLERY_PHOTO: {
                List<String> imageList = data.getStringArrayListExtra("data");
                DebugLog.d(TAG, "Multiple: uri = " + imageList);
                if (mMediaPickerListener != null) {
                    mMediaPickerListener.onMultipleMediaChanged(imageList);
                }
                break;
            }
        }
    }

    private boolean isIntentAvailable(Context context, Intent intent) {
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return !list.isEmpty();
    }

    public void startGalleryAction() {
        Intent intent = new Intent(mActivity, GalleryActivity.class);
        intent.putExtra(IntentConstants.INTENT_LIMIT_EXTRA, mLimit);
        mActivity.startActivityForResult(intent, REQUEST_CODE_GALLERY_PHOTO);
    }

    @SuppressLint("InlinedApi")
    private void openSystemPickImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        if (!isIntentAvailable(mActivity, photoPickerIntent)) {
            photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        }
        photoPickerIntent.setType("image/*");
        photoPickerIntent.addCategory(Intent.CATEGORY_OPENABLE);
        mActivity.startActivityForResult(photoPickerIntent, REQUEST_CODE_PICK_IMAGE);
    }

    private void openSystemCamera() {
        // 保存拍照路径
        File rootDir = UIUtils.getBestCacheDir(mActivity, Constants.FILE_TEMP_DIR);
        this.mTakeFile = new File(rootDir, System.currentTimeMillis() + ".jpg");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTakeFile));
        mActivity.startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
    }


    public void setOnMediaPickerListener(OnMediaPickerListener listener) {
        this.mMediaPickerListener = listener;
    }


    public interface OnMediaPickerListener {

        /**
         * 选取单张图片回调函数
         *
         * @param imagePath
         */
        void onSingleMediaChanged(String imagePath);

        /**
         * 选取多张图片回调函数
         *
         * @param imageList
         */
        void onMultipleMediaChanged(List<String> imageList);
    }
}
