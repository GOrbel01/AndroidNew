package com.example.squall.ffcharapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.squall.ffcharapp.chars.FFChar;
import com.example.squall.ffcharapp.chars.Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Squall on 22/08/2015.
 */
public class FFCharAdapter extends BaseAdapter {

    private final List<FFChar> ffChars = new ArrayList<FFChar>();

    private final Context context;

    public FFCharAdapter(Context context) {
        this.context = context;
    }

    public void add(FFChar ffChar) {
        ffChars.add(ffChar);
        notifyDataSetChanged();
    }

    public void clear() {
        ffChars.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return ffChars.size();
    }

    @Override
    public Object getItem(int position) {
        return ffChars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final FFChar ffChar = (FFChar) getItem(position);

        RelativeLayout itemLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.list_item, null);

        final TextView nameContent = (TextView) itemLayout.findViewById(R.id.nameContent);
        nameContent.setText(ffChar.getName());

        final TextView gameContent = (TextView) itemLayout.findViewById(R.id.gameContent);
        gameContent.setText(Game.getGame(ffChar.getGameEnum()));

        final ImageView charImage = (ImageView) itemLayout.findViewById(R.id.ffCharImage);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        InputStream is;
        Bitmap ffCharImage;
        try {
            is = context.getAssets().open("images/" + ffChar.getImageName());
            ffCharImage = BitmapFunctions.decodeBitmap(is);
            Bitmap temp = Bitmap.createScaledBitmap(ffCharImage, 140, 200, false);
            charImage.setImageBitmap(temp);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Pressed on Char Named: " + ffChar.getName(), Toast.LENGTH_LONG).show();
            }
        });

        return itemLayout;
    }
}
