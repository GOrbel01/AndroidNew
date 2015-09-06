package com.example.squall.fragmentsapp.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.squall.fragmentsapp.DataStore;

/**
 * Created by Squall on 03/09/2015.
 */
public class WordFragment extends ListFragment {
    OnListItemSelected mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layout = android.R.layout.simple_list_item_activated_1;

        setListAdapter(new ArrayAdapter<>(getActivity(), layout, DataStore.words));
    }

    @Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
//        if (getFragmentManager().findFragmentById(R.id.definition_fragment) != null) {
//            Log.d("IN_TWO_PANE", "Two Pane Mode!");
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnListItemSelected) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCallback.itemSelected(position);
        getListView().setItemChecked(position, true);
    }
}
