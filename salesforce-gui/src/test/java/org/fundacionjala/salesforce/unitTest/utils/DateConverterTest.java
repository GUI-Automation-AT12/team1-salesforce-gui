package org.fundacionjala.salesforce.unitTest.utils;

import org.fundacionjala.salesforce.utils.DateConverter;
import org.junit.Assert;
import org.testng.annotations.Test;
import java.util.Calendar;
import java.util.Date;

/**
 * [MR] Tests for DateConverter util class.
 */
public class DateConverterTest {

    /**
     * Tests String conversion for TODAY to a valid date.
     */
    @Test
    public void dateConversionForTodayTest() {
        Calendar calendar = Calendar.getInstance();
        Date actual = DateConverter.convertTextToDate("TODAY");
        Date expected = calendar.getTime();
        Assert.assertEquals(actual.toString(), expected.toString());
    }

    /**
     * Tests String conversion for Tomorrow to a valid date..
     */
    @Test
    public void dateConversionForTomorrowTest() {
        Calendar calendar = Calendar.getInstance();
        Date actual = DateConverter.convertTextToDate("Tomorrow");
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date expected = calendar.getTime();
        Assert.assertEquals(actual.toString(), expected.toString());
    }

    /**
     * Tests String conversion for YESTERday to a valid date.
     */
    @Test
    public void dateConversionForYesterdayTest() {
        Calendar calendar = Calendar.getInstance();
        Date actual = DateConverter.convertTextToDate("YESTERday");
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date expected = calendar.getTime();
        Assert.assertEquals(actual.toString(), expected.toString());
    }

    /**
     * Tests that the date conversion to Formatted String should not be the same before conversion.
     */
    @Test
    public void stringConversionForTodayToDateTest() {
        Calendar calendar = Calendar.getInstance();
        String actual = DateConverter.convertDateToFormattedText(calendar.getTime());
        String notExpected = calendar.getTime().toString();
        Assert.assertNotEquals(actual, notExpected);
    }
}
