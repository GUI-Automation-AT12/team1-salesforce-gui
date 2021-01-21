package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.When;
import org.fundacionjala.salesforce.utils.PageTransporter;

import java.net.MalformedURLException;

/**
 * This class contains the CommonSteps.
 */
public class CommonSteps {

    /**
     * [SL] Goes to specific page.
     *
     * @param page
     * @throws MalformedURLException
     */
    @When("I go to {string}")
    public void goTo(final String page) throws MalformedURLException {
        PageTransporter.navigateToPage(page);
    }
}
