package com.sdx.mobile.tucao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sdx.mobile.tucao.R;
import com.sdx.mobile.tucao.util.DeviceUtils;
import com.sdx.mobile.tucao.util.JumpUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: UITopicImageView
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/7 18:40
 * Desc:
 */
public class UITopicImageView extends FrameLayout {
    private static final int COLUMN_SIZE = 3;

    private Context context;
    private int mSpaceWidth;
    private int mColumnWidth;

    public UITopicImageView(Context context) {
        this(context, null);
    }

    public UITopicImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context);
    }

    private void setupView(Context context) {
        this.context = context;
        mSpaceWidth = (int) DeviceUtils.dp2px(context, 5);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mColumnWidth = (getMeasuredWidth() - (COLUMN_SIZE - 1) * mSpaceWidth) / COLUMN_SIZE;
        updateLayoutParams();
    }

    public void updateView(List<String> imageList) {
        if (imageList == null || imageList.size() == 0) {
            setVisibility(View.GONE);
            return;
        }

        removeAllViews();
        setTag(imageList);
        setVisibility(View.VISIBLE);
        LayoutParams params = null;

        int size = imageList.size();
        for (int i = 0; i < size; i++) {
            params = new LayoutParams(mColumnWidth, mColumnWidth);
            params.leftMargin = (mColumnWidth + mSpaceWidth) * (i % COLUMN_SIZE);
            params.topMargin = (mSpaceWidth + mColumnWidth) * (i / COLUMN_SIZE);
            addView(createView(i, imageList.get(i)), params);
        }
    }

    private void updateLayoutParams() {
        LayoutParams params = null;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            params = (LayoutParams) view.getLayoutParams();
            if (params == null) {
                params = new LayoutParams(mColumnWidth, mColumnWidth);
            } else {
                params.width = mColumnWidth;
                params.height = mColumnWidth;
            }
            params.leftMargin = (mColumnWidth + mSpaceWidth) * (i % COLUMN_SIZE);
            params.topMargin = (mColumnWidth + mSpaceWidth) * (i / COLUMN_SIZE);
            view.setLayoutParams(params);
        }
    }

    private View createView(final int position, final String imageUrl) {
        ImageView imageView = (ImageView) View.inflate(context, R.layout.widget_topic_image_view, null);
        updatePhoto(imageView, imageUrl);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.startPhotoAction(v.getContext(), position, (ArrayList<String>) getTag());
            }
        });
        return imageView;
    }

    private void updatePhoto(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl)
                .placeholder(R.drawable.color_placeholder_drawable)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
