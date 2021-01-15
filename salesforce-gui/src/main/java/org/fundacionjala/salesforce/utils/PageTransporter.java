package org.fundacionjala.salesforce.utils;

import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.fundacionjala.core.throwables.PropertiesReadingException;
import org.fundacionjala.salesforce.config.SalesforceProperties;

import java.net.MalformedURLException;
import java.net.URL;

public final class PageTransporter {

    private PageTransporter() {
    }

    private static void navigateTo(final String url) throws MalformedURLException {
        WebDriverManager.getInstance().getWebDriver().navigate().to(new URL(url));
    }

    /**
     * Allows to navigate to Salesforce Login Page.
     * @throws PropertiesReadingException
     * @throws MalformedURLException
     */
    public static void navigateToLoginPage() throws PropertiesReadingException, MalformedURLException {
        navigateTo(SalesforceProperties.getInstance().getLoginUrl());

    }
}
