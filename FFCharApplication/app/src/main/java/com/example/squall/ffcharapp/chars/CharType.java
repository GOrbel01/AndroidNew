package com.example.squall.ffcharapp.chars;

/**
 * Created by Cloud on 27/08/2015.
 */
public enum CharType {
    SOLDIER, THIEF, MERCENARY, CASTER, INVALID;

    public static CharType getType(String type) {
        switch(type) {
            case "Soldier": {
                return CharType.SOLDIER;
            }
            case "Thief": {
                return CharType.THIEF;
            }
            case "Mercenary": {
                return CharType.MERCENARY;
            }
            case "Caster": {
                return CharType.CASTER;
            }
            default: {
                return CharType.INVALID;
            }
        }
    }

    public static String getType(CharType type) {
        switch(type) {
            case SOLDIER: {
                return "Soldier";
            }
            case THIEF: {
                return "Thief";
            }
            case MERCENARY: {
                return "Mercenary";
            }
            case CASTER: {
                return "Caster";
            }
            default: {
                return "Invalid";
            }
        }
    }
}
