package com.sdx.mobile.tucao.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.sdx.mobile.tucao.R;
import com.sdx.mobile.tucao.util.Toaster;
import com.sdx.mobile.tucao.util.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Name: CommentPopupWindow
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/11 14:35
 * Desc:
 */
public class CommentPopupWindow {

    @Bind(R.id.id_post_comment_editview)
    EditText mContentEditText;

    private int mTopicId;
    private Context mContext;
    private PopupWindow mPopupWindow;

    public CommentPopupWindow(final Context context) {
        mContext = context;
        final View contentView = View.inflate(context, R.layout.adapter_homepage_comment_popup_view, null);
        ButterKnife.bind(this, contentView);

        mPopupWindow = new PopupWindow(contentView);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    public void setmTopicId(int mTopicId) {
        this.mTopicId = mTopicId;
    }

    public void showWindow(View anchor) {
        if (!mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(anchor, Gravity.BOTTOM, 0, 0);
            UIUtils.delayShowSoftInputFromWindow(mContentEditText, 0);
        }
    }

    public void dismissWindow() {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public void clearText() {
        mContentEditText.setText("");
    }

    public String getCommentData() {
        return mContentEditText.getText().toString();
    }

    @OnClick(R.id.id_post_comment_button)
    public void onClick(View view) {
        if (TextUtils.isEmpty(mContentEditText.getText())) {
            Toaster.show(mContext, R.string.string_view_topic_detail_comment_not_empty);
            return;
        }

        dismissWindow();
        EventBus.getDefault().post(new EventCommentData(mTopicId,
                mContentEditText.getText().toString(), mContext));
    }

    public static class EventCommentData {
        public int topicId;
        public String content;
        public Context context;

        public EventCommentData(int topicId, String content, Context context) {
            this.topicId = topicId;
            this.content = content;
            this.context = context;
        }
    }
}
