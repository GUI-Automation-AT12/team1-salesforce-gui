package org.fundacionjala.salesforce.cucumber.stepdefs.personalInformation;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.fundacionjala.salesforce.ui.skins.SkinManager;
import org.fundacionjala.salesforce.ui.skins.abstractPage.personalInformation.AbstractEditPersonalInformation;
import org.fundacionjala.salesforce.utils.PageTransporter;

import java.net.MalformedURLException;
import java.util.Map;

import org.testng.Assert;

public class EditStep {

    private AbstractEditPersonalInformation personalInformation;

    /**
     * Goes to specific page.
     *
     * @param page
     * @throws MalformedURLException
     */
    @When("I go to {string}")
    public void goTo(final String page) throws MalformedURLException {
        PageTransporter.navigateToPage(page);
    }

    /**
     * Edits the personal information.
     *
     * @param formData
     */
    @And("I edit my personal information with the following data")
    public void editMyPersonalInformationWithTheFollowingData(final Map<String, String> formData) {
        personalInformation = SkinManager.getInstance().getSkinFactory().personalInformation();
        personalInformation.update(formData);
    }

    /**
     * Verifies the personal information should be updated.
     */
    @Then("The personal information table should be updated")
    public void validatePersonalInformationTableShouldBeUpdated() {
        Map<String, String> expected = personalInformation.getPersonalInformationAsMap();
        Map<String, String> actual = personalInformation.getWebFromAsMap();
        Assert.assertEquals(actual, expected);
    }
}
