package com.mahendra.tvprogram.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class MLBaseXmlPullParser {
    protected XmlPullParser mXmlPullParser;
    protected int mEventType;

    public MLBaseXmlPullParser() throws XmlPullParserException {
        XmlPullParserFactory factory = MLXmlPullParserFactory.getInstance();
        mXmlPullParser = factory.newPullParser();
    }
}
