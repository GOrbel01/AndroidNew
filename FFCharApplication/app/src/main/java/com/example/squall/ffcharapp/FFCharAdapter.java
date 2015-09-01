package com.example.squall.ffcharapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.squall.ffcharapp.chars.CharType;
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
        RelativeLayout itemLayout;
        if (convertView == null) {
            itemLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.list_item, null);
        } else {
            itemLayout = (RelativeLayout) convertView;
        }

        ViewHolder holder = new ViewHolder();

        holder.nameContent = (TextView) itemLayout.findViewById(R.id.nameContent);
        holder.nameContent.setText(ffChar.getName());

        holder.gameContent = (TextView) itemLayout.findViewById(R.id.gameContent);
        holder.gameContent.setText(Game.getGame(ffChar.getGameEnum()));

        holder.classContent = (TextView) itemLayout.findViewById(R.id.classContent);
        holder.classContent.setText(CharType.getType(ffChar.getType()));

        holder.charIcon = (ImageView) itemLayout.findViewById(R.id.ffCharImage);

        BitmapWorker bitmapWorker = new BitmapWorker(context, holder.charIcon, ffChar.getImageName());
        bitmapWorker.execute(200, 265);

        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Pressed on Char Named: " + ffChar.getName(), Toast.LENGTH_LONG).show();
                Intent startInventoryIntent = new Intent(context, InventoryActivity.class);
                startInventoryIntent.putExtra("CHAR_NAME", ffChar.getName());
                context.startActivity(startInventoryIntent);
            }
        });
        Log.d("MAIN_LIST", "Created Main List");
        return itemLayout;
    }

    static class ViewHolder {
        TextView nameContent;
        TextView gameContent;
        TextView classContent;
        ImageView charIcon;
        int position;
    }
}
