package org.fundacionjala.salesforce.cucumber.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.fundacionjala.core.api.client.RequestManager;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountEditPage.AbstractAccountEditPage;
import org.fundacionjala.salesforce.utils.ApiResponseDataExtractor;
import org.fundacionjala.salesforce.constants.AccountConstants;
import org.fundacionjala.salesforce.ui.context.Context;
import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.ui.skins.ISkinFactory;
import org.fundacionjala.salesforce.ui.skins.SkinManager;
import org.fundacionjala.salesforce.utils.DateConverter;
import org.fundacionjala.salesforce.utils.PageTransporter;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * [MR] StepDefinitions class for salesforce Accounts.
 */
public class AccountSteps {

    private final Context context;
    private Account account;
    private ISkinFactory skin = SkinManager.getInstance().getSkinFactory();
    private String incorrectAssertionMessage = "The %1$s from %2$s does not match with the %1$s edited previously.";

    /**
     * [MR] Adds Dependency injection to share Context information.
     *
     * @param sharedContext
     */
    public AccountSteps(final Context sharedContext) {
        this.context = sharedContext;
    }

    /**
     * [MR] Creates an account from UI and update related account entity.
     *
     * @param accountInfo to create a new Account
     */
    @When("I create an Account with the following data")
    public void createAnAccount(final Map accountInfo) {
        account = new Account();
        account.setInformation(accountInfo);
        account.setUpdatedFields(accountInfo.keySet());

        skin.getAccountsPage().goToAccountCreation();
        skin.getAccountCreationPage().fillAccountInformation(account.getUpdatedFields(), account);

        account.setId(skin.getAccountDetailsPage().getAccountId());
        context.setAccount(account);
    }

    /**
     * [MR] Makes assertions for Account Details and the recently account entity.
     */
    @Then("Account's new data should be displayed at details")
    public void verifyAccountDataIsDisplayedAtDetails() {
        Map<String, String> actualAccountDetails = skin.getAccountDetailsPage().
                getAccountDetails(account.getUpdatedFields());
        Map<String, String> expectedAccountDetails = account.getAccountInfo();
        SoftAssert softAssert = new SoftAssert();
        actualAccountDetails.forEach((field, actualValue) -> {
            softAssert.assertTrue(actualValue.startsWith(expectedAccountDetails.get(field)),
            String.format(incorrectAssertionMessage, field, "Account Details Page"));
        });
        softAssert.assertAll();
    }

    /**
     * [MR] Makes assertions with the data from Accounts Table and the recently account entity.
     *
     * @throws MalformedURLException for invalid navigation
     */
    @Then("Account's new data should be displayed in Accounts table")
    public void verifyAccountDataIsDisplayedInAccountsTable() throws MalformedURLException {
        PageTransporter.navigateToPage("ACCOUNTS");
        Map<String, String> actualTableData = skin.getAccountsPage().
                getAccountDataFromTable(account.getId());
        Map<String, String> expectedTableData = account.getAccountInfo(actualTableData.keySet());
        SoftAssert softAssert = new SoftAssert();
        actualTableData.forEach((field, actualValue) -> {
            softAssert.assertEquals(actualValue, expectedTableData.get(field),
            String.format(incorrectAssertionMessage, field, "Account Table"));
        });
        softAssert.assertAll();
    }

    /**
     * [MR] Makes assertions with the data gotten via API and the edited fields of the account entity.
     */
    @Then("the API response about the Account should contain the new data")
    public void verifyThatTheApiResponseAboutTheAccountContainsTheNewData() {
        Response response = RequestManager.get("/Account/" + account.getId());
        Map<String, String> actualApiResponseData = ApiResponseDataExtractor
                .getDataFromApi(response, account.getUpdatedFields());
        Map<String, String> expectedApiResponseData = account.getAccountInfo();
        SoftAssert softAssert = new SoftAssert();
        actualApiResponseData.forEach((field, actualValue) -> {
            if (!field.equals(AccountConstants.PARENT_ACCOUNT_KEY)) {
                softAssert.assertEquals(actualValue, expectedApiResponseData.get(field),
                String.format(incorrectAssertionMessage, field, "Account API response"));
            } else {
                softAssert.assertEquals(actualValue, account.getParentAccount().getId(),
                String.format(incorrectAssertionMessage, field, "Account API response"));
            }
        });
        softAssert.assertAll();
    }

    /**
     * [SL] Goes to into the first account created.
     *
     * @throws MalformedURLException for invalid navigation
     */

    @When("I go to Existent Account details")
    public void goToExistentAccountDetails() throws MalformedURLException {
        Account contextAccount = context.getAccountList().stream().findFirst().get();
        PageTransporter.navigateToPage("ACCOUNT DETAILS", contextAccount.getId());
    }

    /**
     * [SL] Changes the sla expiration date.
     *
     * @param date a constant what represent the date
     */
    @When("I edit the Account's SLA Expiration Date with the following {string}")
    public void editTheAccountSSLAExpirationDateWithTheFollowingDate(final String date) {
        AbstractAccountEditPage accountEditPage = skin.getAccountDetailsPage().getAccountEditPage();
        accountEditPage.changeSLAExpirationDate(date);
    }

    /**
     * [SL] Displays the new sla expiration date in the details of the account.
     *
     * @param date a constant what represent the date
     */
    @Then("Account's new SLA Expiration {string} should be displayed at details")
    public void accountSNewSLAExpirationDateShouldBeDisplayedAtDetails(final String date) {
        String fieldName = "SLA Expiration Date";
        Set<String> table = new HashSet<>();
        table.add(fieldName);
        Map<String, String> actualAccountDetails = skin.getAccountDetailsPage().getAccountDetails(table);
        String expected = DateConverter.convertDateToFormattedText(DateConverter.convertTextToDate(date));
        Assert.assertEquals(actualAccountDetails.get(fieldName), expected);
    }

    /**
     * [SL] Displays the "Last Modified By" field with the date of TODAY, because the account was a edit today.
     *
     * @param date a constant what represent the date
     */
    @When("the edition datetime should be updated with the {string} date")
    public void theEditionDatetimeShouldBeUpdated(final String date) {
        String fieldName = "Last Modified By";
        Set<String> table = new HashSet<>();
        table.add(fieldName);
        Map<String, String> actualAccountDetails = skin.getAccountDetailsPage().getAccountDetails(table);
        String expected = DateConverter.convertDateToFormattedText(DateConverter.convertTextToDate(date));
        Assert.assertTrue(actualAccountDetails.get(fieldName).contains(expected));
    }
}
