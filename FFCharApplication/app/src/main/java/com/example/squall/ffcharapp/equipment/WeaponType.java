package com.example.squall.ffcharapp.equipment;

/**
 * Created by Cloud on 28/08/2015.
 */
public enum WeaponType {
        SWORD, DAGGERS, GUN, GUNBLADE, INVALID;

    public static WeaponType getType(String type) {
        switch(type) {
            case "Gunblade": {
                return WeaponType.GUNBLADE;
            }
            case "Gun": {
                return WeaponType.GUN;
            }
            case "Daggers": {
                return WeaponType.DAGGERS;
            }
            case "Sword": {
                return WeaponType.SWORD;
            }
            default: {
                return WeaponType.INVALID;
            }
        }
    }

    public static String getType(WeaponType type) {
        switch(type) {
            case GUNBLADE: {
                return "Gunblade";
            }
            case GUN: {
                return "Gun";
            }
            case DAGGERS: {
                return "Daggers";
            }
            case SWORD: {
                return "Sword";
            }
            default: {
                return "Invalid";
            }
        }
    }
}
