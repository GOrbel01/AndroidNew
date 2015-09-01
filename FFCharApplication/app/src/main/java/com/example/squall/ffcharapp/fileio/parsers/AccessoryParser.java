package com.example.squall.ffcharapp.fileio.parsers;

import android.util.Log;
import android.util.Xml;

import com.example.squall.ffcharapp.equipment.Accessory;
import com.example.squall.ffcharapp.equipment.LegArmour;
import com.example.squall.ffcharapp.fileio.parsers.functions.ParseFunctions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cloud on 01/09/2015.
 */
public class AccessoryParser implements FFGameXmlParser<Accessory> {
    private static final String ns = null;
    private static final String MAIN_TAG = "accessories";

    private static final AccessoryParser accessoryParser = new AccessoryParser();

    private AccessoryParser() {

    }

    public static AccessoryParser getInstance() {
        return accessoryParser;
    }

    public List<Accessory> parse(InputStream in) throws XmlPullParserException, IOException {
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
            if (name.equals(Accessory.TAG)) {
                entries.add(readArmour(parser));
            }
            else {
                ParseFunctions.skip(parser);
            }
        }
        return entries;
    }

    private Accessory readArmour(XmlPullParser parser) throws XmlPullParserException, IOException, NumberFormatException {
        parser.require(XmlPullParser.START_TAG, ns, Accessory.TAG);
        String name = null;
        String plusStat = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String pName = parser.getName();
            if (pName.equals("name")) {
                name = ParseFunctions.readSimpleElement(parser, "name");
            }
            else if (pName.equals("plusStat")) {
                plusStat = ParseFunctions.readSimpleElement(parser, "plusStat");
            }
            else {
                ParseFunctions.skip(parser);
            }
        }
        if (!ParseFunctions.isValidNumber(plusStat)) throw new NumberFormatException("Number expected in XML but found non-numeric value");
        Log.d("ACC", name);
        Log.d("ACC", plusStat);
        //TODO replace with Factory
        return new Accessory(name, Integer.parseInt(plusStat));
    }
}
