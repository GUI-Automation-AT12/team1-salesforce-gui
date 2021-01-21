package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.When;
import org.fundacionjala.salesforce.utils.PageTransporter;

import java.net.MalformedURLException;

/**
 * [MR] StepDef class to navigation steps.
 */
public class NavigationSteps {

    /**
     * Goes to some page.
     *
     * @param pageName of page to go
     * @throws MalformedURLException
     */
    @When("I go to {string} page")
    public void goToPage(final String pageName) throws MalformedURLException {
        PageTransporter.navigateToPage(pageName);
    }
}
