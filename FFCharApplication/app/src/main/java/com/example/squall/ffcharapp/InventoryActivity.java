package com.example.squall.ffcharapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.squall.ffcharapp.chars.FFChar;
import com.example.squall.ffcharapp.equipment.Accessory;
import com.example.squall.ffcharapp.equipment.ChestArmour;
import com.example.squall.ffcharapp.equipment.LegArmour;
import com.example.squall.ffcharapp.equipment.Weapon;
import com.example.squall.ffcharapp.fileio.DataManager;


/**
 * Created by Cloud on 28/08/2015.
 */
public class InventoryActivity extends Activity {
    private Spinner weaponSpinner;
    private Spinner chestArmourSpinner;
    private Spinner legArmourSpinner;

    private ImageView charImage;

    private FFChar currentChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_layout);

        charImage = (ImageView) findViewById(R.id.inv_charImage);

        String charSelection;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                charSelection = extras.getString("CHAR_NAME");
            }
            else {
                charSelection = null;
            }
        }
        else {
            charSelection = savedInstanceState.getString("CHAR_NAME");
        }
        currentChar = DataManager.getFFChar(charSelection);

        ViewHolder holder = new ViewHolder();

        BitmapWorker bitmapWorker = new BitmapWorker(this, charImage, currentChar.getImageName());
        bitmapWorker.execute(500, 500);

        holder.weaponSpinner = (Spinner) findViewById(R.id.weaponSpinner);
        ArrayAdapter<Weapon> weapons = new ArrayAdapter<Weapon>(this,
                R.layout.dropdown_item, DataManager.getWeaponData());
        holder.weaponSpinner.setAdapter(weapons);

        holder.chestArmourSpinner = (Spinner) findViewById(R.id.chestSpinner);
        ArrayAdapter<ChestArmour> chestArms = new ArrayAdapter<ChestArmour>(this,
                R.layout.dropdown_item, DataManager.getChestArmourData());
        holder.chestArmourSpinner.setAdapter(chestArms);

        holder.legArmourSpinner = (Spinner) findViewById(R.id.legSpinner);
        ArrayAdapter<LegArmour> legArms = new ArrayAdapter<LegArmour>(this,
                R.layout.dropdown_item, DataManager.getLegArmourData());
        holder.legArmourSpinner.setAdapter(legArms);

        holder.accessorySpinner = (Spinner) findViewById(R.id.accSpinner);
        ArrayAdapter<Accessory> accs = new ArrayAdapter<Accessory>(this,
                R.layout.dropdown_item, DataManager.getAccessoryData());
        holder.accessorySpinner.setAdapter(accs);
    }

    static class ViewHolder {
        Spinner weaponSpinner;
        Spinner chestArmourSpinner;
        Spinner legArmourSpinner;
        Spinner accessorySpinner;
        int position;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("CHAR_NAME", currentChar.getName());
    }
}
