package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.fundacionjala.salesforce.ui.context.Context;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.homePage.components.resultsPanel.AbstractResultsPanel;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.searchResultsPage.AbstractSearchResultsPage;
import org.fundacionjala.salesforce.ui.skins.ISkinFactory;
import org.fundacionjala.salesforce.ui.skins.SkinManager;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

/**
 * [MR] Class that contains Salesforce Searching Step Definitions.
 */
public class SearchSteps {

    private ISkinFactory skin = SkinManager.getInstance().getSkinFactory();
    private String textToSearch;
    private String textToSearchWithoutAsterisk;

    //Pages
    private AbstractResultsPanel resultsPanel;
    private AbstractSearchResultsPage resultsPage;

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
    @When("I set the following text {string} in the searching tool without asterisk")
    public void setTextInTheSearchingTool(final String text) {
        textToSearch = text;
        textToSearchWithoutAsterisk = text.replace("*", "");
        resultsPanel = skin.getHomePage().search(textToSearchWithoutAsterisk);
    }

    /**
     * [MR] Verify that the suggested results contain the inputted text.
     */
    @Then("suggested results should contain the inputted text")
    public void verifySuggestedResultsContainInputtedText() {
        SoftAssert softAssert = new SoftAssert();
        for (String result: resultsPanel.getResults()) {
            softAssert.assertTrue(result.contains(textToSearchWithoutAsterisk),
                    "The result: " + result + " not contains the inputted text: " + textToSearchWithoutAsterisk);
        }
        softAssert.assertAll();
    }

    /**
     * [MR] Add an asterisk to search text box and go to Search Results Page.
     */
    @When("I go to search results")
    public void goToTheSearchResultsAddingAnAsteriskToInputtedText() {
        resultsPage = skin.getHomePage().goToResultsPage(textToSearch);
    }

    /**
     * [MR] Verify if data in Account section of Results Page matches with data from related csv file.
     *
     * @param section to check data
     * @param fileName for assert data
     */
    @Then("the result data in the {string} section should match with the {string}")
    public void verifyResultDataInASectionShouldMatchWithTheCsvFile(final String section, final String fileName) {
        System.out.println("Example: " + textToSearch);
        List<Map<String, String>> actualData = resultsPage.getDataAsListOfMaps(section);
        System.out.println(">>>END OF EXAMPLE<<<");
    }
}
