package org.fundacionjala.salesforce.ui.pageObjects.account.accountCreationPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.constants.AccountConstants;
import org.fundacionjala.salesforce.constants.TagConstants;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.ClassicAccountDetailsPage;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.entities.Account;
import org.fundacionjala.salesforce.ui.pageObjects.account.accountDetailsPage.IAccountDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Set;

/**
 * [MR] ClassicAccountCreationPage object.
 */
public class ClassicAccountCreationPage extends BasePage implements IAccountCreationPage {



    @FindBy(css = "input[name='save']")
    private WebElement saveBtn;

    private String inputLocatorXpath = "//td[preceding-sibling::td/label[text()='%1$s']][1]//%2$s[@type='text']";

    private String selectLocatorXpath = "//td[preceding-sibling::td/label[text()='%s']][1]//select";

    private void clickSaveBtn() {
        GuiInteractioner.clickWebElement(saveBtn);
    }

    private void fillTextBox(final String labelName, final String tagType, final String textToFill) {
        By inputLocator = By.xpath(String.format(inputLocatorXpath, labelName, tagType));
        GuiInteractioner.setInputText(inputLocator, textToFill);
    }

    private void fillDropdown(final String labelName, final String option) {
        By selectLocator = By.xpath(String.format(selectLocatorXpath, labelName));
        Select dropdown = new Select(getDriver().findElement(selectLocator));
        dropdown.selectByVisibleText(option);
    }

    private HashMap<String, Runnable> composeMapStrategy(final Account account) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();
        strategyMap.put(AccountConstants.NAME_KEY, () -> fillTextBox("Account Name",
                TagConstants.INPUT_TAG, account.getName()));
        strategyMap.put(AccountConstants.SITE_KEY, () -> fillTextBox("Account Site",
                TagConstants.INPUT_TAG, account.getSite()));
        strategyMap.put(AccountConstants.PHONE_KEY, () -> fillTextBox("Phone",
                TagConstants.INPUT_TAG, account.getPhone()));
        strategyMap.put(AccountConstants.PARENT_ACCOUNT_KEY, () -> fillTextBox("Parent Account",
                TagConstants.INPUT_TAG, account.getParentAccount().getName()));
        strategyMap.put(AccountConstants.RATING_KEY, () -> fillDropdown("Rating", account.getRating()));
        strategyMap.put(AccountConstants.BILLING_CITY_KEY, () -> fillTextBox("Billing City",
                TagConstants.INPUT_TAG, account.getBillingCity()));
        strategyMap.put(AccountConstants.DESCRIPTION_KEY, () -> fillTextBox("Description",
                TagConstants.TEXTAREA_TAG, account.getDescription()));
        return strategyMap;
    }

    private void setInformation(final Set<String> formFields, final Account account) {
        HashMap<String, Runnable> strategyMap = composeMapStrategy(account);
        formFields.forEach(key -> strategyMap.get(key).run());
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
        return new ClassicAccountDetailsPage();
    }

    @Override
    protected void waitLoadPage() {
    }
}
