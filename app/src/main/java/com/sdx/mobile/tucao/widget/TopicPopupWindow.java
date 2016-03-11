package com.sdx.mobile.tucao.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.sdx.mobile.tucao.R;
import com.sdx.mobile.tucao.model.TopicModel;
import com.sdx.mobile.tucao.util.DeviceUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Name: TopicPopupWindow
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/8 17:10
 * Desc:
 */
public class TopicPopupWindow {

    private int mOffsetX;
    private int mOffsetY;
    private Context mContext;
    private TopicModel mTopicModel;
    private PopupWindow mPopupWindow;

    public TopicPopupWindow(Context context) {
        mContext = context;
        View contentView = View.inflate(context, R.layout.adapter_homepage_updown_popup_view, null);
        ButterKnife.bind(this, contentView);

        mPopupWindow = new PopupWindow(contentView);
        mPopupWindow.setWidth((int) DeviceUtils.dp2px(context, 120));
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setOutsideTouchable(true);

        mOffsetX = (int) DeviceUtils.dp2px(context, 50);
        mOffsetY = (int) DeviceUtils.dp2px(context, 55);
    }

    public void setmTopicModel(TopicModel mTopicModel) {
        this.mTopicModel = mTopicModel;
    }

    public void showWindow(View anchor) {
        if (!mPopupWindow.isShowing()) {
            mPopupWindow.showAsDropDown(anchor, mOffsetX, -mOffsetY);
        }
    }

    public void dismissWindow() {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    @OnClick({
            R.id.id_popup_up_view,
            R.id.id_popup_down_view
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_popup_up_view:
                dismissWindow();
                EventBus.getDefault().post(new EventPopupData(
                        mTopicModel, EventPopupData.TYPE_UP, mContext));
                break;
            case R.id.id_popup_down_view:
                dismissWindow();
                EventBus.getDefault().post(new EventPopupData(
                        mTopicModel, EventPopupData.TYPE_DOWN, mContext));
                break;
        }
    }

    public static class EventPopupData {
        public static final String TYPE_UP = "up";
        public static final String TYPE_DOWN = "down";

        public String type;
        public Context context;
        public TopicModel topicModel;

        public EventPopupData(TopicModel topicModel,
                              String type, Context context) {
            this.type = type;
            this.context = context;
            this.topicModel = topicModel;
            this.context = context;
        }
    }
}
