package com.example.squall.ffcharapp.fileio.parsers;

import android.util.Log;
import android.util.Xml;

import com.example.squall.ffcharapp.equipment.Armour;
import com.example.squall.ffcharapp.equipment.LegArmour;
import com.example.squall.ffcharapp.equipment.Weapon;
import com.example.squall.ffcharapp.fileio.parsers.functions.ParseFunctions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cloud on 29/08/2015.
 */
public class LegArmourParser implements FFGameXmlParser<LegArmour> {
    private static final String ns = null;
    private static final String MAIN_TAG = "legarmours";

    private static final LegArmourParser legArmourParser = new LegArmourParser();

    private LegArmourParser() {

    }

    public static LegArmourParser getInstance() {
        return legArmourParser;
    }

    public List<LegArmour> parse(InputStream in) throws XmlPullParserException, IOException {
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
            if (name.equals(LegArmour.TAG)) {
                entries.add(readArmour(parser));
            }
            else {
                ParseFunctions.skip(parser);
            }
        }
        return entries;
    }

    private LegArmour readArmour(XmlPullParser parser) throws XmlPullParserException, IOException, IllegalArgumentException {
        parser.require(XmlPullParser.START_TAG, ns, LegArmour.TAG);
        String name = null;
        String defense = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String pName = parser.getName();
            if (pName.equals("name")) {
                name = ParseFunctions.readSimpleElement(parser, "name");
            }
            else if (pName.equals("defense")) {
                defense = ParseFunctions.readSimpleElement(parser, "defense");
            }
            else {
                ParseFunctions.skip(parser);
            }
        }
        if (!ParseFunctions.isValidNumber(defense)) throw new IllegalArgumentException("Number expected in XML but found non-numeric value");
        Log.d("ARMOUR", name);
        Log.d("ARMOUR", defense);
        //TODO replace with Factory
        return new LegArmour(name, Integer.parseInt(defense));
    }
}
