package com.sdx.mobile.tucao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.sdx.mobile.tucao.R;
import com.sdx.mobile.tucao.constant.Constants;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Name: ImageFragment
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/17 16:34
 * Desc:
 */
public class ImageFragment extends Fragment implements Constants {
    private static final String EXTRA_IMAGE_URL = "imageUrl";

    @Bind(R.id.photoLoading)
    View mLoadingView;
    @Bind(R.id.photoImage)
    ImageView mImageView;

    public static ImageFragment newInstance(String src) {
        ImageFragment f = new ImageFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_IMAGE_URL, src);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_image, container, false);
        ButterKnife.bind(this, convertView);
        return convertView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String imageUrl = getArguments().getString(EXTRA_IMAGE_URL);
        if (imageUrl.endsWith("!small")) {
            imageUrl = imageUrl.replace("small", "medium");
        }

        Glide.with(this).load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        mImageView.setImageDrawable(resource);
                        mLoadingView.setVisibility(View.GONE);
                    }
                });
    }
}
