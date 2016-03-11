package com.sdx.mobile.tucao.app;

import android.content.Context;
import android.os.Environment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.ViewTarget;
import com.sdx.mobile.tucao.R;

/**
 * Name: GlideModule
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2015/12/16 17:13
 * Desc:
 */
public class FlickrGlideModule implements GlideModule {
    private static final int DEFAULT_DISK_SIZE = 100 * 1024 * 1024;

    @Override
    public void applyOptions(final Context context, GlideBuilder builder) {
        DiskCache.Factory diskCacheFactory = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            diskCacheFactory = new ExternalCacheDiskCacheFactory(context, DEFAULT_DISK_SIZE);
        }
        if (diskCacheFactory == null) {
            diskCacheFactory = new InternalCacheDiskCacheFactory(context, DEFAULT_DISK_SIZE);
        }
        builder.setDiskCache(diskCacheFactory);
        ViewTarget.setTagId(R.id.image_tag);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}
