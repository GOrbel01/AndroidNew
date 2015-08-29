package com.example.squall.ffcharapp.factory;

import com.example.squall.ffcharapp.chars.FFChar;
import com.example.squall.ffcharapp.equipment.ChestArmour;
import com.example.squall.ffcharapp.equipment.LegArmour;
import com.example.squall.ffcharapp.equipment.Weapon;
import com.example.squall.ffcharapp.fileio.parsers.ChestArmourParser;
import com.example.squall.ffcharapp.fileio.parsers.FFCharParser;
import com.example.squall.ffcharapp.fileio.parsers.FFGameXmlParser;
import com.example.squall.ffcharapp.fileio.parsers.LegArmourParser;
import com.example.squall.ffcharapp.fileio.parsers.WeaponParser;

/**
 * Created by Cloud on 28/08/2015.
 */
/*
    Used to abstract Parser singleton instance retrieval
 */
public class ParserMaker {
    public static FFGameXmlParser getInstance(String choice) {
        if (choice.equals(Weapon.TAG)) {
            return WeaponParser.getInstance();
        }
        else if (choice.equals(FFChar.TAG)) {
            return FFCharParser.getInstance();
        }
        else if (choice.equals(LegArmour.TAG)) {
            return LegArmourParser.getInstance();
        }
        else if (choice.equals(ChestArmour.TAG)) {
            return ChestArmourParser.getInstance();
        }
        else return null;
    }
}
