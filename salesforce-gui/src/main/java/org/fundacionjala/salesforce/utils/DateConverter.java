package org.fundacionjala.salesforce.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;

/**
 * [MR] Util class to handle Dates in Salesforce.
 */
public final class DateConverter {

    /**
     * Default class constructor.
     */
    private DateConverter() {
    }

    /**
     * Convert a Text provided to a Date.
     * @param text to parse
     * @return date
     */
    public static Date convertTextToDate(final String text) {
        Calendar calendar = Calendar.getInstance();
        switch (text.toUpperCase()) {
            case "TODAY":
                break;
            case "TOMORROW":
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                break;
            case "YESTERDAY":
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                break;
            case "NEXT WEEK":
                calendar.add(Calendar.DAY_OF_WEEK, 1);
                break;
            case "NEXT MONTH":
                calendar.add(Calendar.MONTH, 1);
            case "NEXT YEAR":
                calendar.add(Calendar.YEAR, 1);
                break;
            default:
                throw new DateTimeException("Invalid String to parse to a valid day.");
        }
        return calendar.getTime();
    }

    /**
     * Convert a date to a String formatted for salesforce.
     * @param date to parse
     * @return formatted String
     */
    public static String convertDateToFormattedText(final Date date) {
        return new SimpleDateFormat("d/M/yyyy").format(date);
    }

    /**
     * Convert a String date to formatted String for Salesforce.
     * @param dateToParse as String
     * @param sourceFormat of the String
     * @return formatted String
     */
    public static String convertStringToFormattedText(final String dateToParse, final String sourceFormat) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(sourceFormat);
            return new SimpleDateFormat("d/M/yyyy").format(formatter.parse(dateToParse));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
