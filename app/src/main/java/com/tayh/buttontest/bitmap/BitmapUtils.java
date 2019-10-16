package com.tayh.buttontest.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author LZY
 * @time 2019/4/12
 */
public class BitmapUtils {

    public static Bitmap compress(String filePath, int targetW, int targetH) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        BitmapFactory.decodeFile(filePath, options);
        int originalW = options.outWidth;
        int originalH = options.outHeight;

        options.inSampleSize = getSimpleSize(originalW, originalH, targetW, targetH);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static Bitmap compress(byte[] bytes, int targetW, int targetH) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        int originalW = options.outWidth;
        int originalH = options.outHeight;

        options.inSampleSize = getSimpleSize(originalW, originalH, targetW, targetH);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }

    private static int getSimpleSize(int originalW, int originalH, int targetW, int targetH) {
        int simpleSize = 1;
        if (originalW > originalH && originalW > targetW) {
            simpleSize = originalW / targetW;
        } else if (originalW < originalH && originalH > targetH) {
            simpleSize = originalH / targetH;
        }
        if (simpleSize <= 0) {
            simpleSize = 1;
        }
        return simpleSize;
    }
}
