package org.fundacionjala.salesforce.config;

import org.fundacionjala.core.throwables.PropertiesReadingException;
import org.fundacionjala.core.utils.PropertiesFileReader;

public class SalesforceProperties {
    private static final String PROPERTIES_FILE_PATH = "salesforce.properties";
    private static SalesforceProperties singleInstance;
    private static PropertiesFileReader propertiesFileReader;

    /**
     * If singleInstance was not instanced before it create a new one, otherwise return the created.
     * @return singleInstance
     */
    public static SalesforceProperties getInstance() throws PropertiesReadingException {
        if (singleInstance == null) {
            singleInstance = new SalesforceProperties();
        }
        return singleInstance;
    }

    private SalesforceProperties() throws PropertiesReadingException {
        propertiesFileReader = new PropertiesFileReader(PROPERTIES_FILE_PATH);
    }


    /**
     * Gets the BaseUrl from the properties file.
     *
     * @return base url.
     */
    public String getBaseUrl() throws PropertiesReadingException {
        return propertiesFileReader.getProperty("baseUrl");
    }

    /**
     * Gets the BaseApiUrl from the properties file.
     *
     * @return base API Url
     */
    public String getBaseApiUrl() throws PropertiesReadingException {
        return propertiesFileReader.getProperty("baseApiUrl");
    }

    /**
     * Gets the BaseApiUrl from the properties file.
     *
     * @return API Login Url
     */
    public String getApiLoginUrl() throws PropertiesReadingException {
        return propertiesFileReader.getProperty("apiLoginUrl");
    }
}
