package com.example.squall.ffcharapp.factory;

import com.example.squall.ffcharapp.fileio.parsers.FFCharParser;
import com.example.squall.ffcharapp.fileio.parsers.FFGameXmlParser;
import com.example.squall.ffcharapp.fileio.parsers.WeaponParser;

/**
 * Created by Cloud on 28/08/2015.
 */
/*
    Used to abstract Parser singleton instance retrieval
 */
public class ParserMaker {
    public static FFGameXmlParser getInstance(String choice) {
        if (choice.equals("weapon")) {
            return WeaponParser.getInstance();
        }
        else if (choice.equals("ffchar")) {
            return FFCharParser.getInstance();
        }
        else return null;
    }
}
