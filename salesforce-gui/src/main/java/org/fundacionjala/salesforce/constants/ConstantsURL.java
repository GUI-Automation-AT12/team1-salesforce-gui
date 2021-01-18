package org.fundacionjala.salesforce.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * [SL] This class contains the alias of the URLs classic and lightning.
 */
public final class ConstantsURL {

    public static final Map<String, String> URL_CLASSIC = new HashMap<>();
    public static final Map<String, String> URL_LIGHTNING = new HashMap<>();

    static {
        URL_LIGHTNING.put("PERSONAL INFORMATION PAGE", "settings/personal/PersonalInformation/home");
    }

    /**
     * Constructor.
     */
    private ConstantsURL() {

    }
}
