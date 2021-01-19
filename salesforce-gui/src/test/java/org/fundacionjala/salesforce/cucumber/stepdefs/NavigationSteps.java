package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.When;
import org.fundacionjala.salesforce.ui.skins.SkinManager;

import java.net.MalformedURLException;

public class NavigationSteps {
    @When("I go to {string} page")
    public void goToPage(final String pageName) throws MalformedURLException {
        SkinManager.getInstance().getSkinFactory().goToPage(pageName);
    }
}
