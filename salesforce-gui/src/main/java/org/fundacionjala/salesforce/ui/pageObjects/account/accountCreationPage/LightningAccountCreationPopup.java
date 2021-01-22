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

/**
 * [MR] LightningAccountCreationPopup object.
 */
public class LightningAccountCreationPopup extends BasePage implements IAccountCreationPage {

    @FindBy(xpath = "//span[.='Parent Account']/../../div//input")
    private WebElement parentAccountSearchBox;

    private String parentAccountXpath = "//div[.='%s'][contains(@class,'primaryLabel')]";


    private void fillParentAccountTextBox(final String text) {
        GuiInteractioner.setInputText(parentAccountSearchBox, text);
        By by = By.xpath(String.format(parentAccountXpath, text));
        GuiInteractioner.clickWebElement(by);
    }

    private String inputLocatorXpath = "//span[.='%1$s']/../../%2$s";

    private void fillTextBox(final String labelName, final String tagType, final String text) {
        By by = By.xpath(String.format(inputLocatorXpath, labelName, tagType));
        GuiInteractioner.setInputText(by, text);
    }

    private String dropdownLocatorXpath = "//span[.='%s' and @class='']/../../div";


    private String selectedXpath = "//a[.='%s']";

    private void fillDropdown(final String labelName, final String option) {
        GuiInteractioner.clickWebElement(By.xpath(String.format(dropdownLocatorXpath, labelName)));
        GuiInteractioner.clickWebElement(By.xpath(String.format(selectedXpath, option)));
    }

    private HashMap<String, Runnable> composeMapStrategy(final Account account) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();
        strategyMap.put(AccountConstants.NAME_KEY, () -> fillTextBox("Account Name", "input", account.getName()));
        strategyMap.put(AccountConstants.SITE_KEY, () -> fillTextBox("Account Site", "input", account.getSite()));
        strategyMap.put(AccountConstants.PHONE_KEY, () -> fillTextBox("Phone", "input", account.getPhone()));
        strategyMap.put(AccountConstants.PARENT_ACCOUNT_KEY, () ->
                fillParentAccountTextBox(account.getParentAccount().getName()));
        strategyMap.put(AccountConstants.RATING_KEY, () -> fillDropdown("Rating", account.getRating()));
        strategyMap.put(AccountConstants.BILLING_CITY_KEY, () ->
                fillTextBox("Billing City", "input", account.getBillingCity()));
        strategyMap.put(AccountConstants.DESCRIPTION_KEY, () ->
                fillTextBox("Description", "textarea", account.getDescription()));
        return strategyMap;
    }

    private void setInformation(final Set<String> formFields, final Account account) {
        HashMap<String, Runnable> strategyMap = composeMapStrategy(account);
        formFields.forEach(key -> strategyMap.get(key).run());
    }

    @FindBy(css = "button[title='Save']")
    private WebElement saveBtn;

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
