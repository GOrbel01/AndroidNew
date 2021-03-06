package com.example.squall.ffcharapp;

import android.app.ListActivity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.squall.ffcharapp.chars.FFChar;
import com.example.squall.ffcharapp.fileio.DataManager;

public class MainActivity extends ListActivity {

    FFCharAdapter ffCharAdapter;
    DataManager dm;

    AssetManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ffCharAdapter = new FFCharAdapter(this);
        getListView().setFooterDividersEnabled(true);

        for (FFChar fchar : DataManager.getFFCharData()) {
            ffCharAdapter.add(fchar);
        }
        getListView().setAdapter(ffCharAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
