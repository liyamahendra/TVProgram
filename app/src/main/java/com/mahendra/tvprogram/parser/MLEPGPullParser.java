package com.mahendra.tvprogram.parser;

import android.util.Log;

import com.mahendra.tvprogram.models.MLChannelModel;
import com.mahendra.tvprogram.models.MLProgrammeModel;
import com.mahendra.tvprogram.net.MLConnection;
import com.mahendra.tvprogram.utils.MLConstants;
import com.mahendra.tvprogram.utils.MLUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class MLEPGPullParser extends MLBaseXmlPullParser implements MLIXmlPullParser {

    private static final String LOG_TAG = MLEPGPullParser.class.getSimpleName();
    private MLConnection mMLConnection;

    private StringBuilder mTagContent = new StringBuilder();
    private MLChannelModel mChannel;
    private MLProgrammeModel mProgramme;
    private ArrayList<MLChannelModel> mChannels;
    private ArrayList<MLProgrammeModel> mProgrammes;

    private boolean isChannelIcon = false;

    // ---------------------------------------------------------------------
    public static final String MAIN_DATA_TAG_NAME = "tv";

    public static final String NODE_CHANNEL = "channel";
    public static final String NODE_DISPLAY_NAME = "display-name";
    public static final String NODE_ICON = "icon";

    public static final String ATTRIBUTE_ID_NO = "idNo";
    public static final String ATTRIBUTE_ID = "id";
    public static final String ATTRIBUTE_CHANNEL = "channel";
    public static final String ATTRIBUTE_SRC = "src";
    public static final String ATTRIBUTE_START = "start";
    public static final String ATTRIBUTE_STOP = "stop";

    public static final String NODE_PROGRAMME = "programme";
    public static final String NODE_TITLE = "title";
    public static final String NODE_SUB_TITLE = "sub-title";
    public static final String NODE_CATEGORY = "category";
    public static final String NODE_DESC = "desc";

    // ---------------------------------------------------------------------

    public MLEPGPullParser(String urlString) throws XmlPullParserException, IOException {
        super();
        mMLConnection = new MLConnection(urlString);
        mXmlPullParser.setInput(mMLConnection.openConnectionAndGetInputStream(), MLConstants.PARSER_DEFAULT_ENCODING);
        mChannels = new ArrayList<>();
        mProgrammes = new ArrayList<>();
    }

    public HashMap<String, Object> parseXmlStream() throws XmlPullParserException, IOException, ParseException {
        if(MLConstants.DEGUB_ON)
            Log.d(LOG_TAG, "parseXmlStream called");

        mEventType = mXmlPullParser.getEventType();
        while (mEventType != XmlPullParser.END_DOCUMENT) {

            parseTag(mEventType);
            mEventType = mXmlPullParser.next();
        }

        if(MLConstants.DEGUB_ON)
            Log.d(LOG_TAG, "Finished parsing tags");

        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("channels", this.mChannels);
        response.put("programmes", this.mProgrammes);

        return response;
    }

    public void parseTag(int event) throws ParseException {

        switch (event) {

            case XmlPullParser.START_DOCUMENT:
                if(mXmlPullParser.getName() != null) {
                    if (mXmlPullParser.getName().equalsIgnoreCase(NODE_CHANNEL)) {
                        mChannels = new ArrayList<MLChannelModel>();
                    } else if (mXmlPullParser.getName().equalsIgnoreCase(NODE_PROGRAMME)) {
                        mProgrammes = new ArrayList<MLProgrammeModel>();
                    }
                }
                break;

            case XmlPullParser.END_DOCUMENT:
                try {
                    mMLConnection.closeStreamAndConnection();
                } catch (IOException ex) {
                    Log.e(LOG_TAG, ex.getMessage());
                    ex.printStackTrace();
                }
                break;
            case XmlPullParser.START_TAG:
                if (mXmlPullParser.getName().equalsIgnoreCase(NODE_CHANNEL)) {
                    this.mChannel = new MLChannelModel();

                    String idNo = mXmlPullParser.getAttributeValue(null, ATTRIBUTE_ID_NO);
                    String id = mXmlPullParser.getAttributeValue(null, ATTRIBUTE_ID);
                    this.mChannel.setIdNo(idNo);
                    this.mChannel.setId(id);

                    isChannelIcon = true;
                } else if (mXmlPullParser.getName().equalsIgnoreCase(NODE_PROGRAMME)) {
                    this.mProgramme = new MLProgrammeModel();

                    String start = mXmlPullParser.getAttributeValue(null, ATTRIBUTE_START);
                    String stop = mXmlPullParser.getAttributeValue(null, ATTRIBUTE_STOP);
                    String idNo = mXmlPullParser.getAttributeValue(null, ATTRIBUTE_ID_NO);
                    String id = mXmlPullParser.getAttributeValue(null, ATTRIBUTE_CHANNEL);

                    //System.out.println("Start: " + start + ", Stop: " + stop + ", idNo: " + idNo + ", id: " + id);

                    this.mProgramme.setStart(MLUtils.getDateFromXQueryDate(start));
                    this.mProgramme.setStop(MLUtils.getDateFromXQueryDate(stop));
                    this.mProgramme.setIdNo(idNo);
                    this.mProgramme.setChannel(id);

                    isChannelIcon = false;
                } else {
                    this.mTagContent.delete(0, this.mTagContent.toString().length());
                }
                break;

            case XmlPullParser.END_TAG:
                if (mXmlPullParser.getName().equalsIgnoreCase(NODE_CHANNEL)) {
                    mChannels.add(this.mChannel);
                } else if (mXmlPullParser.getName().equalsIgnoreCase(NODE_PROGRAMME)) {
                    mProgrammes.add(this.mProgramme);
                } else if (mXmlPullParser.getName().equalsIgnoreCase(NODE_DISPLAY_NAME)) {
                    this.mChannel.setDisplayName(this.mTagContent.toString());
                } else if (mXmlPullParser.getName().equalsIgnoreCase(NODE_TITLE)) {
                    this.mProgramme.setTitle(this.mTagContent.toString());
                } else if (mXmlPullParser.getName().equalsIgnoreCase(NODE_SUB_TITLE)) {
                    this.mProgramme.setSubTitle(this.mTagContent.toString());
                } else if (mXmlPullParser.getName().equalsIgnoreCase(NODE_CATEGORY)) {
                    this.mProgramme.setCategory(this.mTagContent.toString());
                } else if (mXmlPullParser.getName().equalsIgnoreCase(NODE_DESC)) {
                    this.mProgramme.setDescription(this.mTagContent.toString());
                } else if (mXmlPullParser.getName().equalsIgnoreCase(NODE_ICON)) {
                    if(isChannelIcon) {
                        String iconURL = mXmlPullParser.getAttributeValue(null, ATTRIBUTE_SRC);
                        this.mChannel.setIconURL(iconURL);
                    } else {
                        String iconURL = mXmlPullParser.getAttributeValue(null, ATTRIBUTE_SRC);
                        this.mProgramme.setIconURL(iconURL);
                    }
                }

                break;

            case XmlPullParser.TEXT:
                this.mTagContent.append(this.mXmlPullParser.getText());
                break;
        }
    }

    public void stopParsing() {
        if(MLConstants.DEGUB_ON)
            Log.d(LOG_TAG, "Stop parsing");
    }

    public ArrayList<MLChannelModel> getmChannels() {
        return mChannels;
    }

    public ArrayList<MLProgrammeModel> getmProgrammes() {
        return mProgrammes;
    }
}