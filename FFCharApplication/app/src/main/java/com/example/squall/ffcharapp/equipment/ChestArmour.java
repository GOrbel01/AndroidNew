package com.example.squall.ffcharapp.equipment;

/**
 * Created by Cloud on 27/08/2015.
 */
public class ChestArmour extends Armour {

    public static final String TAG = "chestarmour";

    public ChestArmour(String name, int defense) {
        super(name, defense, ArmourSlot.CHEST);
    }
}
