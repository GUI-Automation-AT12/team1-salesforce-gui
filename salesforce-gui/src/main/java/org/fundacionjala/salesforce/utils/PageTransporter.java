package org.fundacionjala.salesforce.utils;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.fundacionjala.salesforce.config.SalesforceProperties;
import org.fundacionjala.salesforce.constants.GenericConstants;
import org.fundacionjala.salesforce.constants.URLConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
            navigateTo(getInstanceUrl() + skinUrl + url + "?source=lex");
        } else {
            skinUrl = SalesforceProperties.getInstance().getLightningSkinUrl();
            navigateTo(getInstanceUrl() + skinUrl + url);
        }
    }

    /**
     * Gets Current Url from the driver.
     *
     * @return currentUrl
     */
    public static String getCurrentUrl() {
        return WebDriverManager.getInstance().getWebDriver().getCurrentUrl();
    }

    private static String getUrlOfPage(final String page) {
        String url;
        switch (SalesforceProperties.getInstance().getSkin()) {
            case GenericConstants.SKIN_CLASSIC:
                if (getCurrentUrl().contains(GenericConstants.SKIN_LIGHTNING)) {
                    GuiInteractioner.clickWebElement(By.cssSelector(".profileTrigger"));
                    GuiInteractioner.clickWebElement(By.cssSelector(".switch-to-aloha"));
                }
                url = URLConstants.URL_CLASSIC.get(page);
                break;
            default:
                url = URLConstants.URL_LIGHTNING.get(page);
        }
        return url;
    }

    /**
     * [SL] Navigate to specific page.
     *
     * @param page to go
     * @throws MalformedURLException
     */
    public static void navigateToPage(final String page) throws MalformedURLException {
        navigateToUrl(getUrlOfPage(page));
    }

    /**
     * [MR] Navigate to specific page receiving another argument to format.
     *
     * @param page to go
     * @param argToFormat to replace in url
     * @throws MalformedURLException
     */
    public static void navigateToPage(final String page, final String argToFormat) throws MalformedURLException {
        navigateToUrl(String.format(getUrlOfPage(page), argToFormat));
    }
}
