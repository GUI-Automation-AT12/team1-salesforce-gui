package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.Given;
import org.fundacionjala.core.api.client.RequestManager;
import org.fundacionjala.core.config.TestExecutionProperties;
import org.fundacionjala.core.config.TestPropertiesSetter;
import org.fundacionjala.core.throwables.PropertiesReadingException;
import org.fundacionjala.salesforce.api.ApiAuthenticator;
import org.fundacionjala.salesforce.ui.context.Context;
import org.fundacionjala.salesforce.ui.entities.User;
import org.fundacionjala.salesforce.ui.commonPages.LoginPage;
import org.fundacionjala.salesforce.ui.skins.SkinManager;
import org.fundacionjala.salesforce.utils.PageTransporter;

import java.io.IOException;

/**
 * [MR] Class that contains Salesforce login Step Definitions.
 */
public class LoginSteps {

    //dependency injection
    private Context context;

    //Entities
    private User user;

    /**
     * Adds Dependency injection to share Context information.
     *
     * @param sharedContext
     */
    public LoginSteps(final Context sharedContext) {
        this.context = sharedContext;
    }

    /**
     * Logs a user in Salesforce UI.
     *
     * @param userAlias
     * @throws IOException
     * @throws PropertiesReadingException
     */
    @Given("I log in to Salesforce with {string} credentials")
    public void logInToSalesforce(final String userAlias) throws IOException, PropertiesReadingException {

        //To run only one scenario uncomment the lines below
        TestExecutionProperties.setRootPath("../salesforce-core/");
        TestPropertiesSetter.setDataProviderThreadCountProp();
        TestPropertiesSetter.setTestBrowser();

        //Updating User Entity to get credentials
        user = context.getUserByAlias(userAlias);

        //Set User Authentication to use Salesforce API in next steps
        RequestManager.setRequestSpec(ApiAuthenticator.getLoggedReqSpec(user));

        //Login from UI
        PageTransporter.navigateToLoginPage();
        LoginPage loginPage = new LoginPage();
        loginPage.login(user.getUsername(), user.getPassword());
        SkinManager.getInstance().getSkinFactory().goHomePage();
    }
}
