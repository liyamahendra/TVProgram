package com.mahendra.tvprogram.parser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public interface MLIXmlPullParser {
    HashMap<String, Object> parseXmlStream() throws XmlPullParserException, IOException, ParseException;
    void parseTag(int eventType) throws ParseException;
}
