package com.example.squall.ffcharapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Squall on 24/08/2015.
 */
public class BitmapFunctions {
    public static Bitmap createBitmap(Resources res, Integer resID) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        Bitmap bitmap = BitmapFactory.decodeResource(res, resID, options);

        final int bitmap_size = 640;

        final int x = bitmap.getWidth();
        final int y = bitmap.getHeight();

        int scaleX = bitmap_size / 3;
        int scaleY = bitmap_size / 2;

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaleX, scaleY, false);
        return scaledBitmap;
    }

    public static Bitmap decodeBitmap(InputStream is, int size) {
            // Decode image size
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, options);

            // The new size we want to scale to
            final int REQUIRED_SIZE=size;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(options.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    options.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }
            scale = size;

            // Decode with inSampleSize
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = scale;
            return BitmapFactory.decodeStream(is, null, options2);
    }
}
