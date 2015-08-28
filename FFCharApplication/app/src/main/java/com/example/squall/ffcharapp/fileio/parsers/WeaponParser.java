package com.example.squall.ffcharapp.fileio.parsers;

import android.util.Log;
import android.util.Xml;

import com.example.squall.ffcharapp.chars.FFChar;
import com.example.squall.ffcharapp.chars.Game;
import com.example.squall.ffcharapp.equipment.Weapon;
import com.example.squall.ffcharapp.equipment.WeaponType;
import com.example.squall.ffcharapp.fileio.parsers.functions.ParseFunctions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cloud on 28/08/2015.
 */
//TODO Dont think the parsing can be abstracted any further than the libs
public class WeaponParser implements FFGameXmlParser<Weapon>  {
    private static final String ns = null;
    private static final String MAIN_TAG = "weapons";

    private static final WeaponParser weaponParser = new WeaponParser();

    private WeaponParser() {

    }

    public static WeaponParser getInstance() {
        return weaponParser;
    }

    public List<Weapon> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, MAIN_TAG);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("weapon")) {
                entries.add(readWeapon(parser));
            }
            else {
                ParseFunctions.skip(parser);
            }
        }
        return entries;
    }

    private Weapon readWeapon(XmlPullParser parser) throws XmlPullParserException, IOException, IllegalArgumentException {
        parser.require(XmlPullParser.START_TAG, ns, "weapon");
        String name = null;
        String ap = null;
        String type = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String pName = parser.getName();
            if (pName.equals("name")) {
                name = ParseFunctions.readSimpleElement(parser, "name");
            }
            else if (pName.equals("ap")) {
                ap = ParseFunctions.readSimpleElement(parser, "ap");
            }
            else if (pName.equals("type")) {
                type = ParseFunctions.readSimpleElement(parser, "type");
            }
            else {
                ParseFunctions.skip(parser);
            }
        }
        if (!ParseFunctions.isValidNumber(ap)) throw new IllegalArgumentException("Number expected in XML but found non-numeric value");
        Log.d("WEAPON", name);
        Log.d("WEAPON", ap);
        //TODO replace with Factory
        return new Weapon(name, Integer.parseInt(ap), WeaponType.getType(type));
    }
}
