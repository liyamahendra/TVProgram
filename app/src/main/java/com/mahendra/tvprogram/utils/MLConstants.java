package com.mahendra.tvprogram.utils;
//import com.mahendra.tvprogram.BuildConfig;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class MLConstants {
    public static boolean DEGUB_ON = true; //BuildConfig.DEBUG;
    public static final String PARSER_DEFAULT_ENCODING = "UTF-8";

    // 20200619130000  0000
    //private static final String PATTERN_XQUERY_DATE = "yyyyMMddHHmmss  zzz";
    private static final String PATTERN_XQUERY_DATE = "yyyyMMddHHmmss";
    public static SimpleDateFormat DATE_FORMAT_XQUERY = new SimpleDateFormat(MLConstants.PATTERN_XQUERY_DATE, Locale.GERMAN);
    public static final TimeZone GERMAN_TIME_ZONE = TimeZone.getTimeZone("CEST");

    public static final String KEY_PARSER_IS_ERROR = "error";
    public static final String KEY_PARSER_ERROR_MESSAGE = "message";
    public static final String EXTRA_CHANNEL_ID = "channel_id";

    public static final String URL_EPG = "https://veusat-epg.azurewebsites.net/epg.php";
}

