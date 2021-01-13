package org.fundacionjala.salesforce.unitTest.utils;

import org.fundacionjala.salesforce.constants.Constants;
import org.fundacionjala.salesforce.utils.DecodingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * [SL] This class contains test for base64Decode.
 */
public class DecodingUtilsTest {
    /**
     * Test to decode a text in base64.
     */
    @Test
    public void base64DecodeValidCode() {
        String textEncode = "SGVsbG8gV29ybGQuLi4gOik=";
        String actual = DecodingUtils.base64Decode(textEncode);
        String expected = "Hello World... :)";
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test to decode a wrong text in base64.
     */
    @Test
    public void base64DecodeInvalidCode() {
        String textEncode = "SVsbG8gV29ybGQuLi4Oi";
        String actual = DecodingUtils.base64Decode(textEncode);
        String expected = Constants.INVALID_TEXT_ENCODE;
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test to check if the ascii is printable.
     */
    @Test
    public void asciiPrintableCharacter() {
        String message = "This character '%c' is not printable";
        SoftAssert softAssertion = new SoftAssert();
        for (char ch = Constants.ASCII_PRINTABLE_START; ch <= Constants.ASCII_PRINTABLE_END; ch++) {
            softAssertion.assertTrue(DecodingUtils.isPrintable(ch), String.format(message, ch));
        }
        softAssertion.assertAll();
    }

    /**
     * Test to check if the ascii is not printable.
     */
    @Test
    public void asciiNotPrintableCharacter() {
        String message = "This character '%c' is printable";
        SoftAssert softAssertion = new SoftAssert();
        for (char ch = 0; ch < Constants.ASCII_PRINTABLE_START; ch++) {
            softAssertion.assertFalse(DecodingUtils.isPrintable(ch), String.format(message, ch));
        }
        softAssertion.assertAll();
    }

    /**
     * Test to check if the string contains valid characters.
     */
    @Test
    public void textContainsValidCharacters() {
        String message = "ABCDEF%$.1234567890-.m";
        Assert.assertTrue(DecodingUtils.isValidString(message));
    }

    /**
     * Test to check if the string contains invalid characters.
     */
    @Test
    public void textContainsInvalidCharacters() {
        String message = "Ĩĩİ";
        Assert.assertFalse(DecodingUtils.isValidString(message));
    }
}
