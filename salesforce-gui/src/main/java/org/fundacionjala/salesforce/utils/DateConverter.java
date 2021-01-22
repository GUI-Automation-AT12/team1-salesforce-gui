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
     *
     * @param text
     * @return
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
            default:
                throw new DateTimeException("Invalid String to parse to a valid day.");
        }
        return calendar.getTime();
    }

    public static String convertDateToFormattedText(final Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}
