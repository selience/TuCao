package com.sdx.mobile.tucao.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sdx.mobile.tucao.app.GlobalContext;
import com.sdx.mobile.tucao.constant.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Name: BitmapUtils
 * User: Lee (darkeet.me@gmail.com)
 * Date: 2016/3/17 13:59
 * Desc:
 */
public class BitmapUtils {

    /**
     * 释放图片占用内存 空间
     *
     * @param bitmap
     */
    public static void recycleBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
    }

    /**
     * 压缩图片大小并存储到本地,用于文件上传
     *
     * @param imagePath
     * @return
     */
    public static String storeImage(String imagePath) {
        Bitmap bitmap = null;
        FileOutputStream fos = null;

        try {
            String fileName = UIUtils.getFileName(imagePath);
            File file = UIUtils.getBestCacheDir(GlobalContext.getInstance(),
                    Constants.FILE_THUMBNAIL_DIR);
            File destFile = new File(file, fileName);
            if (destFile.exists()) {
                return destFile.getPath();
            }

            fos = new FileOutputStream(destFile);
            bitmap = decodeFile(imagePath, 480, 800);
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fos);
                fos.flush();
                return destFile.getPath();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                recycleBitmap(bitmap);
            } catch (IOException e) {
            }
        }

        return imagePath;
    }

    public static Bitmap decodeFile(String filePath, int targetWidth, int targetHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, targetWidth, targetHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        try {
            return BitmapFactory.decodeFile(filePath, options);
        } catch (Exception ex) {
            ex.printStackTrace();
        } catch (OutOfMemoryError error) {
            System.gc();
            error.printStackTrace();
        }
        return null;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
        }

        return inSampleSize;
    }
}

