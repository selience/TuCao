package com.sdx.mobile.tucao.util;

import android.content.Context;
import android.content.Intent;
import com.sdx.mobile.tucao.activity.DetailActivity;
import com.sdx.mobile.tucao.activity.MainActivity;
import com.sdx.mobile.tucao.activity.PhotoActivity;
import com.sdx.mobile.tucao.activity.PublishActivity;
import com.sdx.mobile.tucao.constant.Constants;
import java.util.ArrayList;

/**
 * Name: JumpUtils
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/17 16:48
 * Desc:
 */
public class JumpUtils implements Constants {

    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void startTopicDetailAction(Context context, int topicId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(INTENT_TOPIC_ID, topicId);
        context.startActivity(intent);
    }

    public static void startPublishAction(Context context, String topicName) {
        Intent intent = new Intent(context, PublishActivity.class);
        intent.putExtra(INTENT_TOPIC_SUBJECT, topicName);
        context.startActivity(intent);
    }

    public static void startPhotoAction(Context context, int position, ArrayList<String> imageList) {
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra(EXTRA_IMAGE_POSTION, position);
        intent.putStringArrayListExtra(EXTRA_IMAGE_LIST, imageList);
        context.startActivity(intent);
    }
}
