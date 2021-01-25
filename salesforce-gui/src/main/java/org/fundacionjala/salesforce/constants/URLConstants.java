package org.fundacionjala.salesforce.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * [SL] This class contains the alias of the URLs classic and lightning.
 */
public final class URLConstants {
    public static final Map<String, String> URL_CLASSIC = new HashMap<>();
    public static final Map<String, String> URL_LIGHTNING = new HashMap<>();

    static {
        URL_LIGHTNING.put("PERSONAL INFORMATION", "settings/personal/PersonalInformation/home");
        URL_LIGHTNING.put("HOME", "page/home");
        URL_LIGHTNING.put("ACCOUNTS", "o/Account/list");
        URL_LIGHTNING.put("OPPORTUNITIES", "o/Opportunity/list");
        URL_LIGHTNING.put("ACCOUNT_DETAILS", "r/Account/%s/view");
        URL_CLASSIC.put("PERSONAL INFORMATION", "setup/personalInformationSetup.apexp");
        URL_CLASSIC.put("HOME", "home/home.jsp");
        URL_CLASSIC.put("ACCOUNTS", "001/o");
        URL_CLASSIC.put("OPPORTUNITIES", "006/o");
        URL_CLASSIC.put("ACCOUNT_DETAILS", "%s");
    }

    /**
     * Constructor.
     */
    private URLConstants() {
    }
}
