package com.mahendra.tvprogram.parser;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class MLXmlPullParserFactory {
    private static XmlPullParserFactory factoryInstance = null;

    private MLXmlPullParserFactory() {

    }

    public static synchronized XmlPullParserFactory getInstance() throws XmlPullParserException {
        if (factoryInstance == null) {
            factoryInstance = XmlPullParserFactory.newInstance();
            factoryInstance.setNamespaceAware(true);
        }

        return factoryInstance;
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
