package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.fundacionjala.salesforce.ui.skins.SkinManager;
import org.fundacionjala.salesforce.ui.skins.abstractPage.personalInformation.AbstractEditPersonalInformationPage;

import java.util.Map;

import org.testng.Assert;

public class PersonalInformationSteps {

    private AbstractEditPersonalInformationPage personalInformation;

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
