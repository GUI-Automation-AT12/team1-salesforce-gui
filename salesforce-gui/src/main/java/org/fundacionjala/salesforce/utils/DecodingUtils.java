package org.fundacionjala.salesforce.utils;

import org.fundacionjala.salesforce.constants.GenericConstants;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * [SL] This class contains utilities for the project.
 */
public final class DecodingUtils {
    /**
     * [SL] Constructor.
     */
    private DecodingUtils() {

    }
    /**
     * [SL] Returns a text encode in base64 in plain text.
     * @param textEncode
     * @return a plain text.
     */
    public static String base64Decode(final String textEncode) {
        byte[] bytes = new byte[0];
        try {
            bytes = textEncode.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        String textDecode = new String(Base64.getDecoder().decode(bytes));
        if (isValidString(textDecode)) {
            return textDecode;
        } else {
            return GenericConstants.INVALID_TEXT_ENCODE;
        }
    }

    /**
     * [SL] Check if the string contains only printable characters.
     * @param value
     * @return if the string is printable.
     */
    public static boolean isValidString(final String value) {
        if (value == null) {
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            if (!isPrintable(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * [SL] Check if the character is printable.
     * @param character
     * @return true if between 32 and 126 inclusive
     */
    public static boolean isPrintable(final char character) {
        return character >= GenericConstants.ASCII_PRINTABLE_START && character <= GenericConstants.ASCII_PRINTABLE_END;
    }
}
