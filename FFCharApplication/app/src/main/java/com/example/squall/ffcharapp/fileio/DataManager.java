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
public class DataManager {
    private static List<Weapon> weaponData;
    private static List<FFChar> ffCharData;
    private static List<ChestArmour> chestArmourData;
    private static List<LegArmour> legArmourData;

    public DataManager(List<FFChar> ffchars, List<Weapon> weps, List<ChestArmour> chests, List<LegArmour> legs) {
        weaponData = weps;
        ffCharData = ffchars;
        chestArmourData = chests;
        legArmourData = legs;
    }

    public static List<FFChar> getFFCharData() {
        return ffCharData;
    }

    public static List<Weapon> getWeaponData() {
        return weaponData;
    }

    public static List<ChestArmour> getChestArmourData() {
        return chestArmourData;
    }

    public static List<LegArmour> getLegArmourData() {
        return legArmourData;
    }
}
