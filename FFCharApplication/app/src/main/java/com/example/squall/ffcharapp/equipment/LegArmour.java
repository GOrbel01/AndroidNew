package com.example.squall.ffcharapp.equipment;

/**
 * Created by Cloud on 27/08/2015.
 */
public class LegArmour extends Armour {

    public static final String TAG = "legarmour";

    public LegArmour(String name, int defense) {
        super(name, defense, ArmourSlot.LEG);
    }
}
