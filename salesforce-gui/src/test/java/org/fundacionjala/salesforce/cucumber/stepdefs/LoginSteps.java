package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.Given;
import org.fundacionjala.core.throwables.PropertiesReadingException;
import org.fundacionjala.salesforce.ui.pages.LoginPage;
import org.fundacionjala.salesforce.utils.PageTransporter;

import java.net.MalformedURLException;

public class LoginSteps {

    @Given("I log in to Salesforce (.*?) credentials")
    public void logInToSalesforce(final String alias) throws MalformedURLException, PropertiesReadingException {

        //To Refactor
        PageTransporter.navigateToLoginPage();
        LoginPage loginPage = new LoginPage();
        loginPage.login("","");
    }
}
