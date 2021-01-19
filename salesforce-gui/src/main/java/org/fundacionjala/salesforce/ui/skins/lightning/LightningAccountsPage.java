package org.fundacionjala.salesforce.ui.skins.lightning;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.fundacionjala.salesforce.ui.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.skins.iPages.IAccountCreationPage;
import org.fundacionjala.salesforce.ui.skins.iPages.IAccountsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LightningAccountsPage extends BasePage implements IAccountsPage {
    @FindBy(xpath = "//a[@title='New']")
    private WebElement newAccountBtn;

    @FindBy(css = "div.actionBody")
    private WebElement newAccountModal;

    private void clickNewAccountBtn() {
        GuiInteractioner.clickWebElement(newAccountBtn);
    }

    @Override
    public IAccountCreationPage goToAccountCreation() {
        clickNewAccountBtn();
        switchToCreationPopUp();
        return new LightningAccountCreationPopup();
    }

    private void switchToCreationPopUp() {
        //WebDriverManager.getInstance().getWebDriver().switchTo().
        //GuiInteractioner.frameToBeAvailableAndSwitchToIt(newAccountModal);
    }
}
