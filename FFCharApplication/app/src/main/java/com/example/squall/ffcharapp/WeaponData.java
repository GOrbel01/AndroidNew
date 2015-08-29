package com.example.squall.ffcharapp;

import com.example.squall.ffcharapp.equipment.Weapon;
import com.example.squall.ffcharapp.factory.ParserMaker;

import java.util.List;

/**
 * Created by Cloud on 28/08/2015.
 */
public class WeaponData {
    private List<Weapon> weaponData;

    private static final WeaponData instance = new WeaponData(ParserMaker.getInstance(Weapon.TAG).parse());

    private WeaponData(List<Weapon> data) {
        weaponData = data;
    }

    public static WeaponData getInstance() {
        return instance;
    }

    public List<Weapon> getWeaponData() {
        return weaponData;
    }

}
