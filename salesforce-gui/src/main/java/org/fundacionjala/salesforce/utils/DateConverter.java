package org.fundacionjala.salesforce.utils;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;

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
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}
