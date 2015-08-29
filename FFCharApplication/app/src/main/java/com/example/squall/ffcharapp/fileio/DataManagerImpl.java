package com.example.squall.ffcharapp.fileio;

import android.content.AsyncTaskLoader;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import com.example.squall.ffcharapp.chars.FFChar;
import com.example.squall.ffcharapp.equipment.ChestArmour;
import com.example.squall.ffcharapp.equipment.LegArmour;
import com.example.squall.ffcharapp.equipment.Weapon;
import com.example.squall.ffcharapp.factory.ParserMaker;
import com.example.squall.ffcharapp.fileio.parsers.FFGameXmlParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * Created by Cloud on 28/08/2015.
 */
public class DataManagerImpl implements DataManager {
    private final List<Weapon> weaponData;
    private final List<FFChar> ffCharData;
    private final List<ChestArmour> chestArmourData;
    private final List<LegArmour> legArmourData;

    public DataManagerImpl(AssetManager am) {
        List<Weapon> tempWep = parseToList("weapon_data.xml", Weapon.TAG, am);
        weaponData = Collections.unmodifiableList(tempWep);
        XmlParseTask<FFChar> parseFFCharData = new XmlParseTask<FFChar>(am);
        List<FFChar> tempFFChar = execute(new String[]{"weapon_data.xml", FFChar.TAG});
        ffCharData = Collections.unmodifiableList(tempFFChar);

        //TODO WHEN COMPLETE IMPL
        chestArmourData = null;
        legArmourData = null;
    }

    public List<FFChar> getFFCharData() {
        return ffCharData;
    }

    public List<Weapon> getWeaponData() {
        return weaponData;
    }

    public List<ChestArmour> getChestArmourData() {
        return chestArmourData;
    }

    public List<LegArmour> getLegArmourData() {
        return legArmourData;
    }
}
