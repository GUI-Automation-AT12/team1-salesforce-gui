package org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.constants.AccountConstants;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.LightningAccountDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.IAccountDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Set;

public class LightningAccountCreationPopup extends BasePage implements IAccountCreationPage {
    @FindBy(xpath = "//label/span[.='Account Name']/../../input")
    private WebElement nameTextBox;

    @FindBy(xpath = "//label/span[.='Account Site']/../../input")
    private WebElement siteTextBox;

    @FindBy(xpath = "//label/span[.='Phone']/../../input")
    private WebElement phoneTextBox;

    @FindBy(xpath = "//label/span[.='Parent Account']/../../div//input")
    private WebElement parentAccountSearchBox;

    private String parentAccountXpath = "//div[.='%s'][contains(@class,'primaryLabel')]";

    @FindBy(xpath = "//span/span[.='Rating']/../../div/div/div")
    private WebElement ratingDropdown;

    private String selectedRatingXpath = "//a[.='%s']";

    @FindBy(xpath = "//label/span[.='Billing City']/../../input")
    private WebElement billingCityTextBox;

    @FindBy(xpath = "//label/span[.='Description']/../../textarea")
    private WebElement descriptionTextArea;

    @FindBy(css = "button[title='Save']")
    private WebElement saveBtn;

    private void fillNameTextBox(final String text) {
        GuiInteractioner.setInputText(nameTextBox, text);
    }

    private void fillSiteTextBox(final String text) {
        GuiInteractioner.setInputText(siteTextBox, text);
    }

    private void fillPhoneTextBox(final String text) {
        GuiInteractioner.setInputText(phoneTextBox, text);
    }

    private void fillParentAccountTextBox(final String text) {
        GuiInteractioner.setInputText(parentAccountSearchBox, text);
        By by = By.xpath(String.format(parentAccountXpath, text));
        GuiInteractioner.clickWebElement(by);
    }

    private void fillRatingDropdown(final String option) {
        GuiInteractioner.clickWebElement(ratingDropdown);
        By by = By.xpath(String.format(selectedRatingXpath, option));
        GuiInteractioner.clickWebElement(by);
    }

    private void fillBillingCityTextBox(final String text) {
        GuiInteractioner.setInputText(billingCityTextBox, text);
    }

    private void fillDescriptionTextArea(final String text) {
        GuiInteractioner.setInputText(descriptionTextArea, text);
    }

    private HashMap<String, Runnable> composeMapStrategy(final Account account) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();
        strategyMap.put(AccountConstants.NAME_KEY, () -> fillNameTextBox(account.getName()));
        strategyMap.put(AccountConstants.SITE_KEY, () -> fillSiteTextBox(account.getSite()));
        strategyMap.put(AccountConstants.PHONE_KEY, () -> fillPhoneTextBox(account.getPhone()));
        strategyMap.put(AccountConstants.PARENT_ACCOUNT_KEY, () ->
                fillParentAccountTextBox(account.getParentAccount().getName()));
        strategyMap.put(AccountConstants.RATING_KEY, () -> fillRatingDropdown(account.getRating()));
        strategyMap.put(AccountConstants.BILLING_CITY_KEY, () -> fillBillingCityTextBox(account.getBillingCity()));
        strategyMap.put(AccountConstants.DESCRIPTION_KEY, () -> fillDescriptionTextArea(account.getDescription()));
        return strategyMap;
    }

    private void setInformation(final Set<String> formFields, final Account account) {
        HashMap<String, Runnable> strategyMap = composeMapStrategy(account);
        formFields.forEach(key -> strategyMap.get(key).run());
    }

    private void clickSaveBtn() {
        GuiInteractioner.clickWebElement(saveBtn);
    }

    /**
     * Fill Account information to create a new one.
     *
     * @param formFields to fill
     * @param account entity to get information to fill
     * @return AccountDetailsPage of the new account
     */
    @Override
    public IAccountDetailsPage fillAccountInformation(final Set<String> formFields, final Account account) {
        setInformation(formFields, account);
        clickSaveBtn();
        return new LightningAccountDetailsPage();
    }
}
