package com.mahendra.tvprogram.utils;

import java.text.ParseException;
import java.util.Date;

public class MLUtils {
    public static Date getDateFromXQueryDate(String xQueryDate) throws ParseException {
        xQueryDate = xQueryDate.split(" ")[0];
        return MLConstants.DATE_FORMAT_XQUERY.parse(xQueryDate);
    }

    public static String formatDateToStoreInSqlite(Date dateToFormat) {

        String finalDateTime = "";

        if (dateToFormat != null) {
            long when = dateToFormat.getTime();
            finalDateTime = String.valueOf(when + MLConstants.GERMAN_TIME_ZONE.getOffset(when));
        }

        return finalDateTime;
    }
}
