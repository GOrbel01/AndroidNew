package com.example.squall.fragmentsapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.squall.fragmentsapp.R;

/**
 * Created by Cloud on 07/09/2015.
 */
public class DefTitleFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.def_title_layout, container, false);
        return view;
    }
}
