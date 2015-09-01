package com.example.squall.ffcharapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by Cloud on 31/08/2015.
 */
public class BitmapWorker extends AsyncTask<Integer, Void, Bitmap> {

        Context mContext;
        ImageView charImage;
        String imageName;

        BitmapWorker(Context context, ImageView view, String imageName) {
            mContext = context;
            charImage = view;
            this.imageName = imageName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Integer... params) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            InputStream is;
            Bitmap ffCharImage = null;
                try {
                    boolean assetExists = Arrays.asList(mContext.getResources().getAssets().list("images")).contains(imageName);
                    if (assetExists) {
                        is = mContext.getAssets().open("images/" + imageName);
                        Bitmap temp = BitmapFactory.decodeStream(is, null, options);
                        ffCharImage = Bitmap.createScaledBitmap(temp, params[0], params[1], true);
                    } else {
                        is = mContext.getAssets().open("images/" + "default.jpg");
                        ffCharImage = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(is), params[0], params[1], true);
                    }
                } catch (FileNotFoundException ex) {
                    Log.d("FILE_REPORT", "Using default for this char");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            return ffCharImage;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
                charImage.setImageBitmap(bitmap);
        }
}
