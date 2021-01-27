package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.When;
import org.fundacionjala.salesforce.ui.context.Context;
import org.fundacionjala.salesforce.ui.skins.ISkinFactory;
import org.fundacionjala.salesforce.ui.skins.SkinManager;

import java.util.List;

public class SearchSteps {

    private ISkinFactory skin = SkinManager.getInstance().getSkinFactory();

    //Context
    private final Context context;

    /**
     * [MR] Adds Dependency injection to share Context information.
     * @param sharedContext
     */
    public SearchSteps(final Context sharedContext) {
        this.context = sharedContext;
    }

    @When("I set the following text {string} in the searching tool")
    public void setTextInTheSearchingTool(final String text) {
        List<String> results;
        results = skin.getHomePage().search(text).getResults();
        for (String r: results) {
            System.out.println(r);
        }
    }
}
