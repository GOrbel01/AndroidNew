package com.example.squall.ffcharapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.squall.ffcharapp.chars.FFChar;
import com.example.squall.ffcharapp.equipment.ChestArmour;
import com.example.squall.ffcharapp.equipment.LegArmour;
import com.example.squall.ffcharapp.equipment.Weapon;
import com.example.squall.ffcharapp.fileio.DataManager;
import com.example.squall.ffcharapp.fileio.parsers.functions.ParseFunctions;

import java.util.List;

/**
 * Created by Cloud on 28/08/2015.
 */
public class MainMenuActivity extends Activity {

    private Button playButton;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = (Button) findViewById(R.id.mainPlayButton);
        new LoadXmlTask(this).execute();
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startGame = new Intent(MainMenuActivity.this, MainActivity.class);
                startActivity(startGame);
            }
        });
    }

    private class LoadXmlTask extends AsyncTask<Void, Integer, DataManager> {

        Context mContext;

        LoadXmlTask(Context context) {
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            //Create a new progress dialog
            progressDialog = new ProgressDialog(MainMenuActivity.this);
            //Set the progress dialog to display a horizontal progress bar
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            //Set the dialog title to 'Loading...'
            progressDialog.setTitle("Loading...");
            //Set the dialog message to 'Loading application View, please wait...'
            progressDialog.setMessage("Loading application View, please wait...");
            //This dialog can't be canceled by pressing the back key
            progressDialog.setCancelable(false);
            //This dialog isn't indeterminate
            progressDialog.setIndeterminate(false);
            //The maximum number of items is 100
            progressDialog.setMax(100);
            //Set the current progress to zero
            progressDialog.setProgress(0);
            //Display the progress dialog
            progressDialog.show();
        }

        @Override
        protected DataManager doInBackground(Void... params) {
            AssetManager am = mContext.getAssets();
            DataManager dm = null;
            try {
                synchronized (this) {
                    this.wait(1000);
                    List<FFChar> ffChars = ParseFunctions.parseToList("char_data.xml", FFChar.TAG, am);
                    publishProgress(25);
                    this.wait(1000);
                    List<Weapon> ffWeps = ParseFunctions.parseToList("weapon_data.xml", Weapon.TAG, am);
                    publishProgress(50);
                    this.wait(1000);
                    List<LegArmour> ffLegs = ParseFunctions.parseToList("armour_leg_data.xml", LegArmour.TAG, am);
                    publishProgress(75);
                    this.wait(1000);
                    List<ChestArmour> ffChests = ParseFunctions.parseToList("armour_chest_data.xml", ChestArmour.TAG, am);
                    publishProgress(100);
                    dm = new DataManager(ffChars, ffWeps, ffChests, ffLegs);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return dm;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(DataManager dataManager) {
            progressDialog.dismiss();
        }
    }
}
