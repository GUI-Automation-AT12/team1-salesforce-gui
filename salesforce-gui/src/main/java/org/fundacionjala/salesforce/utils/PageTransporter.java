package org.fundacionjala.salesforce.utils;

import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.fundacionjala.salesforce.config.SalesforceProperties;
import org.fundacionjala.salesforce.constants.ConstantsURL;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.fundacionjala.salesforce.constants.Constants;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * [MR] Class in charge of transport the WebDriver to a specific Url.
 */
public final class PageTransporter {

    private static String instanceUrl;

    private PageTransporter() {
    }

    private static String getInstanceUrl() {
        if (instanceUrl == null) {
            try {
                instanceUrl = initInstanceUrl();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        return instanceUrl;
    }

    private static String initInstanceUrl() throws MalformedURLException {
        WebDriverManager.getInstance().getWebDriverWait().until(
                ExpectedConditions.not(ExpectedConditions.urlContains("login")));
        String[] splitUrl = WebDriverManager.getInstance().getWebDriver().getCurrentUrl().split("\\.");
        if (splitUrl.length > 0) {
            return splitUrl[0].replace("--c", "");
        } else {
            throw new MalformedURLException("The URL is empty or incorrect.");
        }
    }

    private static void navigateTo(final String url) throws MalformedURLException {
        WebDriverManager.getInstance().getWebDriver().navigate().to(new URL(url));
    }

    /**
     * Allows to navigate to Salesforce Login Page.
     *
     * @throws MalformedURLException
     */
    public static void navigateToLoginPage() throws MalformedURLException {
        navigateTo(SalesforceProperties.getInstance().getLoginUrl());
    }

    /**
     * Allows to navigate to a specific Url inside the Salesforce Skin provided.
     *
     * @param url to go to
     * @throws MalformedURLException
     */
    public static void navigateToUrl(final String url) throws MalformedURLException {
        String skinUrl;
        if ("classic".equals(SalesforceProperties.getInstance().getSkin())) {
            skinUrl = SalesforceProperties.getInstance().getClassicSkinUrl();
        } else {
            skinUrl = SalesforceProperties.getInstance().getLightningSkinUrl();
        }
        navigateTo(getInstanceUrl() + skinUrl + url);
    }

    /**
     * [SL] Navigate to specific page.
     *
     * @param page
     * @throws MalformedURLException
     */
    public static void navigateToPage(final String page) throws MalformedURLException {
        String skin = SalesforceProperties.getInstance().getSkin();
        switch (skin) {
            case Constants.SKIN_CLASSIC:
                navigateToUrl(ConstantsURL.URL_CLASSIC.get(page));
                break;
            case Constants.SKIN_LIGHTNING:
                navigateToUrl(ConstantsURL.URL_LIGHTNING.get(page));
                break;
            default:
                navigateToUrl(ConstantsURL.URL_LIGHTNING.get(page));
        }
    }
}
