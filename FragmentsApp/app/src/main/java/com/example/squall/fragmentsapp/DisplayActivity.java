package com.example.squall.fragmentsapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


import com.example.squall.fragmentsapp.fragments.DefinitionFragment;

/**
 * Created by Cloud on 06/09/2015.
 */
public class DisplayActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_definitions);
        textView = (TextView) findViewById(R.id.defText);
        Bundle args = getIntent().getExtras();
        int pos = args.getInt(DefinitionFragment.LAST_POSITION);
        textView.setText(DataStore.data.get(DataStore.words[pos]));
    }
}
