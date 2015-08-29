package com.example.squall.ffcharapp.fileio;

import com.example.squall.ffcharapp.chars.FFChar;
import com.example.squall.ffcharapp.equipment.ChestArmour;
import com.example.squall.ffcharapp.equipment.LegArmour;
import com.example.squall.ffcharapp.equipment.Weapon;

import java.util.List;

/**
 * Created by Squall on 29/08/2015.
 */
public interface DataManager {
    List<FFChar> getFFCharData();
    List<Weapon> getWeaponData();
    List<ChestArmour> getChestArmourData();
    List<LegArmour> getLegArmourData();
}
