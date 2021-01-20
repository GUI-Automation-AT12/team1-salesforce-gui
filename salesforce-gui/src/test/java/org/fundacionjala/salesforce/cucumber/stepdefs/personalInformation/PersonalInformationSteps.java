package org.fundacionjala.salesforce.cucumber.stepdefs.personalInformation;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.fundacionjala.salesforce.ui.skins.SkinManager;
import org.fundacionjala.salesforce.ui.PersonalInformation.AbstractEditPersonalInformationPage;

import java.util.Map;

import org.testng.Assert;

/**
 * This class contains the steps for personal information.
 */
public class PersonalInformationSteps {

    private AbstractEditPersonalInformationPage personalInformation;

    /**
     * [SL] Edits the personal information.
     *
     * @param formData
     */
    @And("I edit my personal information with the following data")
    public void editMyPersonalInformationWithTheFollowingData(final Map<String, String> formData) {
        personalInformation = SkinManager.getInstance().getSkinFactory().personalInformation();
        personalInformation.update(formData);
    }

    /**
     * [SL] Verifies the personal information should be updated.
     */
    @Then("The personal information table should be updated")
    public void validatePersonalInformationTableShouldBeUpdated() {
        Map<String, String> expected = personalInformation.getPersonalInformationAsMap();
        Map<String, String> actual = personalInformation.getWebFromAsMap();
        Assert.assertEquals(actual, expected);
    }
}
