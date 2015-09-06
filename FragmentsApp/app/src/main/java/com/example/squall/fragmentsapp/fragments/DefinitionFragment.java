package com.example.squall.fragmentsapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.squall.fragmentsapp.DataStore;
import com.example.squall.fragmentsapp.R;

/**
 * Created by Squall on 03/09/2015.
 */
public class DefinitionFragment extends Fragment {
    public final static String LAST_POSITION = "position";
    int mCurrentPosition = -1;

    private TextView defText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(LAST_POSITION);
        }
        Log.d("RUN_CREVIEW", "Created View");
        View view = inflater.inflate(R.layout.word_definitions, container, false);
        defText = (TextView) view.findViewById(R.id.defText);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("RUN_CREATACTIV", "Created Activity");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("START_CALL", "Called onStart()");
        Bundle args = getArguments();
        if (args != null) {
            Log.d("UPDATING", "Updating Here");
            updateDefinitionView(args.getInt(LAST_POSITION));
        } else if (mCurrentPosition != -1) {
            updateDefinitionView(mCurrentPosition);
        }
    }

    public void updateDefinitionView(int position) {
        defText.setText(DataStore.data.get(DataStore.words[position]));
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LAST_POSITION, mCurrentPosition);
    }
}
