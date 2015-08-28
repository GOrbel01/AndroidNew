package com.example.squall.ffcharapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.squall.ffcharapp.R;

/**
 * Created by Squall on 14/08/2015.
 */
public class SecondActivity extends Activity {

    private TextView secondText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        secondText = (TextView) findViewById(R.id.second_text);
        secondText.setText("I am the second Activity...!");
    }
}
