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
        } else {
            skinUrl = SalesforceProperties.getInstance().getLightningSkinUrl();
        }
        navigateTo(getInstanceUrl() + skinUrl + url);
    }

    /**
     * Gets Current Url from the driver.
     *
     * @return currentUrl
     */
    public static String getCurrentUrl() {
        return WebDriverManager.getInstance().getWebDriver().getCurrentUrl();
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
            case GenericConstants.SKIN_CLASSIC:
                if (getCurrentUrl().contains(GenericConstants.SKIN_LIGHTNING)) {
                    GuiInteractioner.clickWebElement(By.cssSelector(".profileTrigger"));
                    GuiInteractioner.clickWebElement(By.cssSelector(".switch-to-aloha"));
                }
                navigateToUrl(URLConstants.URL_CLASSIC.get(page) + "?source=lex");
                break;
            case GenericConstants.SKIN_LIGHTNING:
                navigateToUrl(URLConstants.URL_LIGHTNING.get(page));
                break;
            default:
                navigateToUrl(URLConstants.URL_LIGHTNING.get(page));
        }
    }
}
