package com.example.squall.ffcharapp.fileio.parsers.functions;

import android.content.res.AssetManager;
import android.util.Log;

import com.example.squall.ffcharapp.chars.FFChar;
import com.example.squall.ffcharapp.factory.ParserMaker;
import com.example.squall.ffcharapp.fileio.parsers.FFCharParser;
import com.example.squall.ffcharapp.fileio.parsers.FFGameXmlParser;
import com.example.squall.ffcharapp.fileio.parsers.WeaponParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Cloud on 28/08/2015.
 */
public class ParseFunctions {
    public static String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    public static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    public static String readSimpleElement(XmlPullParser parser, String elementName) throws IOException, XmlPullParserException  {
        parser.require(XmlPullParser.START_TAG, null, elementName);
        String result = ParseFunctions.readText(parser);
        parser.require(XmlPullParser.END_TAG, null, elementName);
        return result;
    }

    public static boolean isValidNumber(String num) {
        boolean isNumber = true;
        for (int i = 0; i < num.length(); i++) {
            if (!Character.isDigit(num.charAt(i))) {
                isNumber = false;
            }
        }
        return isNumber;
    }

    public static <T> List<T> parseToList(String dataName, String choice, AssetManager am) {
        List<T> list = null;
        FFGameXmlParser parser = ParserMaker.getInstance(choice);
        InputStream is = null;
        try {
            is = am.open("sourceData/" + dataName);
            list = parser.parse(is);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (XmlPullParserException ex) {
            ex.printStackTrace();
        }
        if (is != null) {
            try {
                is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }
}
