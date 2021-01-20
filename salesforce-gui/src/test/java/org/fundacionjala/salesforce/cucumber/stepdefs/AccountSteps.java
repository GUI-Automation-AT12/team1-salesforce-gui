package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.ui.skins.SkinManager;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.util.Map;

public class AccountSteps {

    private Account account;

    @Then("I create an Account with the following data")
    public void createAnAccountWithTheFollowingData(final Map accountInfo) {
        //Updating Entity
        account = new Account();
        account.setInformation(accountInfo);
        account.setUpdatedFields(accountInfo.keySet());

        SkinManager.getInstance().getSkinFactory().createNewAccount(account.getUpdatedFields(), account);
    }


    @Then("Account's new data should be displayed at details")
    public void accountSNewDataShouldBeDisplayedAtDetails() {
        Map<String, String> actualAccountDetails = SkinManager.getInstance().getSkinFactory().getAccountDetails(account.getUpdatedFields());
        Map<String, String> expectedAccountDetails = account.getAccountInfo();
        SoftAssert softAssert = new SoftAssert();
        actualAccountDetails.forEach((field, actualValue) -> {
            softAssert.assertEquals(actualValue, expectedAccountDetails.get(field),
            "The " + field + " from Account Details Page does not match with the " + field + " edited previously.");
        });
        softAssert.assertAll();
        account.setId(SkinManager.getInstance().getSkinFactory().getAccountId());
    }


    @Then("Account's new data should be displayed in Accounts table")
    public void accountSNewDataShouldBeDisplayedInAccountsTable() throws MalformedURLException {
        SkinManager.getInstance().getSkinFactory().goToPage("ACCOUNTS");
        Map<String, String> actualTableData = SkinManager.getInstance().getSkinFactory().getAccountDataFromTable(account.getId());
        Map<String, String> expectedTableData = account.getAccountInfo(actualTableData.keySet());
        SoftAssert softAssert = new SoftAssert();
        actualTableData.forEach((field, actualValue) -> {
            softAssert.assertEquals(actualValue, expectedTableData.get(field),
                    "The " + field + " of Account from Accounts Table does not match with the " + field + " edited previously.");
        });
        softAssert.assertAll();
    }
}
