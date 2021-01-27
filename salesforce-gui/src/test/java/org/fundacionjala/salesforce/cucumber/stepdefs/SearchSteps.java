package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.fundacionjala.salesforce.ui.context.Context;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.components.resultsPanel.AbstractResultsPanel;
import org.fundacionjala.salesforce.ui.skins.ISkinFactory;
import org.fundacionjala.salesforce.ui.skins.SkinManager;
import org.testng.asserts.SoftAssert;

/**
 * [MR] Class that contains Salesforce Searching Step Definitions.
 */
public class SearchSteps {

    private ISkinFactory skin = SkinManager.getInstance().getSkinFactory();
    private String textToSearch;

    //Pages
    private AbstractResultsPanel resultsPanel;

    //Context
    private final Context context;

    /**
     * [MR] Adds Dependency injection to share Context information.
     * @param sharedContext
     */
    public SearchSteps(final Context sharedContext) {
        this.context = sharedContext;
    }

    /**
     * [MR] Sets the text to search in Salesforce.
     *
     * @param text to search
     */
    @When("I set the following text {string} in the searching tool")
    public void setTextInTheSearchingTool(final String text) {
        resultsPanel = skin.getHomePage().search(text);
        textToSearch = text;
    }

    /**
     * [MR] Verify that the suggested results contain the inputted text.
     */
    @Then("suggested results should contain the inputted text")
    public void verifySuggestedResultsContainInputtedText() {
        SoftAssert softAssert = new SoftAssert();
        for (String result: resultsPanel.getResults()) {
            softAssert.assertTrue(result.contains(textToSearch),
                    "The result: " + result + "not contains the inputted text: " + textToSearch);
        }
        softAssert.assertAll();
    }
}
