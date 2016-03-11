package com.sdx.mobile.tucao.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sdx.mobile.tucao.R;
import com.sdx.mobile.tucao.util.DeviceUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Name: UIPickImageView
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/7 18:40
 * Desc:
 */
public class UIPickImageView extends FrameLayout {
    private static final int COLUMN_SIZE = 4;
    private static final int MAX_COUNT = 9;

    private Context context;
    private int mSpaceWidth;
    private int mColumnWidth;
    private Set<String> mImageList;
    private OnClickListener mOnItemClickListener;

    public UIPickImageView(Context context) {
        this(context, null);
    }

    public UIPickImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context);
    }

    private void setupView(Context context) {
        this.context = context;
        this.mImageList = new HashSet<>();
        mSpaceWidth = (int) DeviceUtils.dp2px(context, 0);

        updateView();
    }

    public Set<String> getImageList() {
        return mImageList;
    }

    public void addItemView(String imagePath) {
        mImageList.add(imagePath);
        updateView();
    }

    public void addItemView(List<String> imageList) {
        mImageList.addAll(imageList);
        updateView();
    }

    public void removeItemView(String imagePath) {
        mImageList.remove(imagePath);
    }

    public void setOnItemClickListener(OnClickListener l) {
        this.mOnItemClickListener = l;
    }

    private void updateView() {
        updateViewList(new ArrayList<String>(mImageList));
    }

    private void updateViewList(List<String> imageList) {
        removeAllViews();
        LayoutParams params = null;

        int size = (imageList != null && imageList.size() > 0) ? imageList.size() : 0;

        for (int i = 0; i < size + 1; i++) {
            if (i >= MAX_COUNT) break;

            params = new LayoutParams(mColumnWidth, mColumnWidth);
            params.leftMargin = (mColumnWidth + mSpaceWidth) * (i % COLUMN_SIZE);
            params.topMargin = (mSpaceWidth + mColumnWidth) * (i / COLUMN_SIZE);

            String imagePath = (i < size) ? imageList.get(i) : null;
            addView(createView(imagePath), params);
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

    private View createView(final String imageUrl) {
        View convertView = View.inflate(context, R.layout.widget_pick_image_view, null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.id_imageView);
        ImageView deleteView = (ImageView) convertView.findViewById(R.id.id_deleteView);
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(imageUrl)) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClick(view);
                    }
                } else {
                    removeView(view);
                    removeItemView(imageUrl);
                }
            }
        });

        if (!TextUtils.isEmpty(imageUrl)) {
            updatePhoto(imageView, imageUrl);
        } else {
            deleteView.setVisibility(View.INVISIBLE);
            imageView.setImageResource(R.drawable.ic_pick_add);
        }

        return convertView;
    }

    private void updatePhoto(ImageView imageView, String imageUrl) {
        imageView.setTag(imageUrl);
        Glide.with(context).load(imageUrl)
                .placeholder(R.drawable.color_placeholder_drawable)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mColumnWidth = (getMeasuredWidth() - getPaddingRight() - (COLUMN_SIZE - 1) * mSpaceWidth) / COLUMN_SIZE;
        updateLayoutParams();
    }
}
