package com.example.squall.ffcharapp.fileio.parsers;

import android.util.Log;
import android.util.Xml;

import com.example.squall.ffcharapp.equipment.ChestArmour;
import com.example.squall.ffcharapp.equipment.LegArmour;
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
public class ChestArmourParser implements FFGameXmlParser<ChestArmour> {
    private static final String ns = null;
    private static final String MAIN_TAG = "chestarmours";

    private static final ChestArmourParser chestArmourParser = new ChestArmourParser();

    private ChestArmourParser() {

    }

    public static ChestArmourParser getInstance() {
        return chestArmourParser;
    }

    public List<ChestArmour> parse(InputStream in) throws XmlPullParserException, IOException {
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
            if (name.equals(ChestArmour.TAG)) {
                entries.add(readArmour(parser));
            }
            else {
                ParseFunctions.skip(parser);
            }
        }
        return entries;
    }

    private ChestArmour readArmour(XmlPullParser parser) throws XmlPullParserException, IOException, NumberFormatException {
        parser.require(XmlPullParser.START_TAG, ns, ChestArmour.TAG);
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
        if (!ParseFunctions.isValidNumber(defense)) throw new NumberFormatException("Number expected in XML but found non-numeric value");
        Log.d("ARMOUR", name);
        Log.d("ARMOUR", defense);
        //TODO replace with Factory
        return new ChestArmour(name, Integer.parseInt(defense));
    }
}
