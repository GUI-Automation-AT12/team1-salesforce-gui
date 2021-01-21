package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.fundacionjala.core.api.client.RequestManager;
import org.fundacionjala.salesforce.api.ApiResponseDataExtractor;
import org.fundacionjala.salesforce.constants.AccountConstants;
import org.fundacionjala.salesforce.ui.context.Context;
import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.ui.skins.ISkinFactory;
import org.fundacionjala.salesforce.ui.skins.SkinManager;
import org.fundacionjala.salesforce.utils.PageTransporter;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.util.Map;

public class AccountSteps {

    private Account account;

    private ISkinFactory skin = SkinManager.getInstance().getSkinFactory();

    //Context
    private final Context context;

    /**
     * Adds Dependency injection to share Context information.
     * @param sharedContext
     */
    public AccountSteps(final Context sharedContext) {
        this.context = sharedContext;
    }

    @Then("I create an Account with the following data")
    public void createAnAccountWithTheFollowingData(final Map accountInfo) {
        //Updating Entity
        account = new Account();
        account.setInformation(accountInfo);
        account.setUpdatedFields(accountInfo.keySet());

        skin.getAccountsPage().goToAccountCreation();
        skin.getAccountCreationPage().fillAccountInformation(account.getUpdatedFields(), account);

        account.setId(skin.getAccountDetailsPage().getAccountId());
        context.setAccount(account);
    }


    @Then("Account's new data should be displayed at details")
    public void accountSNewDataShouldBeDisplayedAtDetails() {
        Map<String, String> actualAccountDetails = skin.getAccountDetailsPage().
                getAccountDetails(account.getUpdatedFields());
        Map<String, String> expectedAccountDetails = account.getAccountInfo();
        SoftAssert softAssert = new SoftAssert();
        actualAccountDetails.forEach((field, actualValue) -> {
            softAssert.assertEquals(actualValue, expectedAccountDetails.get(field),
            "The " + field + " from Account Details Page does not match with the " + field + " edited previously.");
        });
        softAssert.assertAll();
    }


    @Then("Account's new data should be displayed in Accounts table")
    public void accountSNewDataShouldBeDisplayedInAccountsTable() throws MalformedURLException {
        PageTransporter.navigateToPage("ACCOUNTS");
        Map<String, String> actualTableData = skin.getAccountsPage().
                getAccountDataFromTable(account.getId());
        Map<String, String> expectedTableData = account.getAccountInfo(actualTableData.keySet());
        SoftAssert softAssert = new SoftAssert();
        actualTableData.forEach((field, actualValue) -> {
            softAssert.assertEquals(actualValue, expectedTableData.get(field),
                    "The " + field + " of Account from Accounts Table does not match with the " + field + " edited previously.");
        });
        softAssert.assertAll();
    }

    @Then("the gotten data via API about the Account should contain the new data")
    public void theGottenDataAboutTheAccountViaAPIShouldContainTheNewData() {
        Response response = RequestManager.get("/Account/" + account.getId());
        Map<String, String> actualApiResponseData = ApiResponseDataExtractor
                .getAccountDataFromApi(response, account.getUpdatedFields());
        Map<String, String> expectedApiResponseData = account.getAccountInfo();
        SoftAssert softAssert = new SoftAssert();
        actualApiResponseData.forEach((field, actualValue) -> {
            if(!field.equals(AccountConstants.PARENT_ACCOUNT_KEY)) {
                softAssert.assertEquals(actualValue, expectedApiResponseData.get(field),
                        "The " + field + " from Account API response does not match with the " + field + " edited previously.");
            } else {
                softAssert.assertEquals(actualValue, account.getParentAccount().getId(),
                        "The " + field + " from Account API response does not match with the " + field + " edited previously.");
            }
        });
        softAssert.assertAll();
    }
}
