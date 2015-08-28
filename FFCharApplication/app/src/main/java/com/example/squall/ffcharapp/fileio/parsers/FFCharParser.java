package com.example.squall.ffcharapp.fileio.parsers;

import android.util.Log;
import android.util.Xml;

import com.example.squall.ffcharapp.chars.FFChar;
import com.example.squall.ffcharapp.chars.Game;
import com.example.squall.ffcharapp.equipment.Weapon;
import com.example.squall.ffcharapp.fileio.parsers.functions.ParseFunctions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cloud on 27/08/2015.
 */
public class FFCharParser implements FFGameXmlParser<FFChar>  {

    private static final String ns = null;
    private static final String MAIN_TAG = "ffchars";

    private static final FFCharParser ffCharParser = new FFCharParser();

    private FFCharParser() {

    }

    public static FFCharParser getInstance() {
        return ffCharParser;
    }

    public List<FFChar> parse(InputStream in) throws XmlPullParserException, IOException {
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
            if (name.equals("ffchar")) {
                entries.add(readFFChar(parser));
            }
            else {
                ParseFunctions.skip(parser);
            }
        }
        return entries;
    }

    private FFChar readFFChar(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "ffchar");
        String name = null;
        String image = null;
        String game = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String pName = parser.getName();
            if (pName.equals("name")) {
                name = ParseFunctions.readSimpleElement(parser, "name");
            }
            else if (pName.equals("game")) {
                game = ParseFunctions.readSimpleElement(parser, "game");
            }
            else if (pName.equals("image")) {
                image = ParseFunctions.readSimpleElement(parser, "image");
            }
            else {
                ParseFunctions.skip(parser);
            }
        }
        Log.d("FCHAR", name);
        //TODO replace with Factory
        return new FFChar(name, Game.getGame(game), image);
    }
}
