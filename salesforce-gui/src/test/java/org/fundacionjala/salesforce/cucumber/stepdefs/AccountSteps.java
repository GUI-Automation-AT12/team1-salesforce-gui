package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.Then;
import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.ui.skins.SkinManager;

import java.util.Map;

public class AccountSteps {

    private Account account;

    @Then("I create an Account with the following data")
    public void createAnAccountWithTheFollowingData(final Map accountInfo) {
        //Updating Entity
        account = new Account();
        account.setInformation(accountInfo);
        SkinManager.getInstance().getSkinFactory().createNewAccount(accountInfo.keySet(), account);
    }
}
