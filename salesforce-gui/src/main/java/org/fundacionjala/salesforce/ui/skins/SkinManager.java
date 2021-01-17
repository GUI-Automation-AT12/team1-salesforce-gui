package org.fundacionjala.salesforce.ui.skins;

import org.fundacionjala.salesforce.config.SalesforceProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * [MR] Singleton class that manage the Skin to run the scenarios.
 */
public final class SkinManager {

    private ISkinFactory skinFactory;
    private static SkinManager skinManager;

    private static Map<String, ISkinFactory> skinsMap = new HashMap<>();

    static {
        skinsMap.put("classic", new ClassicSkinFactory());
        skinsMap.put("lightning", new LightningSkinFactory());
    }

    /**
     * Tries to get the SingleInstance for SkinManager singleton class.
     *
     * @return skinManager single instance
     */
    public static SkinManager getInstance() {
        if (skinManager == null) {
            skinManager = new SkinManager();
        }
        return skinManager;
    }

    private SkinManager() {
        skinFactory = skinsMap.get(SalesforceProperties.getInstance().getSkin());
    }

    /**
     * The Skin Factory object of the Single Instance.
     * @return skinFactory.
     */
    public ISkinFactory getSkinFactory() {
        return skinFactory;
    }
}
