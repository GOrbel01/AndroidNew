package com.example.squall.ffcharapp.fileio.parsers;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Cloud on 28/08/2015.
 */
public interface FFGameXmlParser<T> {
    List<T> parse(InputStream in) throws XmlPullParserException, IOException;
}
