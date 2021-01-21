package org.fundacionjala.salesforce.constants;

import java.util.HashMap;
import java.util.Map;

public final class URLConstants {
    public static final Map<String, String> URL_CLASSIC = new HashMap<>();
    public static final Map<String, String> URL_LIGHTNING = new HashMap<>();

    static {
        URL_LIGHTNING.put("PERSONAL INFORMATION PAGE", "settings/personal/PersonalInformation/home");
        URL_LIGHTNING.put("HOME", "page/home");
        URL_LIGHTNING.put("ACCOUNTS", "o/Account/list");
    }

    static {
        URL_CLASSIC.put("PERSONAL INFORMATION PAGE", "setup/personalInformationSetup.apexp");
        URL_CLASSIC.put("HOME", "home/home.jsp?source=lex");
        URL_CLASSIC.put("ACCOUNTS", "001/o");
    }

    /**
     * Constructor.
     */
    private URLConstants() {
    }
}
