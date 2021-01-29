package org.fundacionjala.salesforce.config;

import org.fundacionjala.core.throwables.PropertiesReadingException;
import org.fundacionjala.core.utils.PropertiesFileReader;

/**
 * [MR] Class that read properties from salesforce.properties file.
 */
public final class SalesforceProperties {

    private static final String PROPERTIES_FILE_PATH = "../salesforce.properties";
    private static SalesforceProperties singleInstance;
    private static PropertiesFileReader propertiesFileReader;

    /**
     * If singleInstance was not instanced before it create a new one, otherwise return the created.
     *
     * @return singleInstance
     */
    public static SalesforceProperties getInstance() {
        if (singleInstance == null) {
            singleInstance = new SalesforceProperties();
        }
        return singleInstance;
    }

    private SalesforceProperties() {
        try {
            propertiesFileReader = new PropertiesFileReader(PROPERTIES_FILE_PATH);
        } catch (PropertiesReadingException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }


    /**
     * Gets the Login Url from the properties file.
     *
     * @return login Url.
     */
    public String getLoginUrl() {
        String baseUrl = null;
        try {
            baseUrl = propertiesFileReader.getProperty("loginUrl");
        } catch (PropertiesReadingException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            return baseUrl;
        }
    }

    /**
     * Gets the BaseLoginUrl from the properties file.
     *
     * @return base url.
     */
    public String getBaseApiUrl() {
        String baseApiUrl = null;
        try {
            baseApiUrl = propertiesFileReader.getProperty("baseApiUrl");
        } catch (PropertiesReadingException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            return baseApiUrl;
        }
    }

    /**
     * Gets the Api Login Url from the salesforce.properties file.
     *
     * @return base API Url
     */
    public String getApiLoginUrl() {
        String apiLoginUrl = null;
        try {
            apiLoginUrl = propertiesFileReader.getProperty("apiLoginUrl");
        } catch (PropertiesReadingException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            return apiLoginUrl;
        }
    }

    /**
     * Gets the skin from the salesforce.properties file.
     *
     * @return skin
     */
    public String getSkin() {
        String skin = null;
        try {
            skin = propertiesFileReader.getProperty("skin");
        } catch (PropertiesReadingException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            return skin;
        }
    }

    /**
     * Gets the classicSkinUrl from the salesforce.properties file.
     *
     * @return classicSkinUrl
     */
    public String getClassicSkinUrl() {
        String classicSkinUrl = null;
        try {
            classicSkinUrl = propertiesFileReader.getProperty("classicSkinUrl");
        } catch (PropertiesReadingException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            return classicSkinUrl;
        }
    }

    /**
     * Gets the lightningSkinUrl from the salesforce.properties file.
     *
     * @return lightningSkinUrl
     */
    public String getLightningSkinUrl() {
        String lightningSkinUrl = null;
        try {
            lightningSkinUrl = propertiesFileReader.getProperty("lightningSkinUrl");
        } catch (PropertiesReadingException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            return lightningSkinUrl;
        }
    }
}
