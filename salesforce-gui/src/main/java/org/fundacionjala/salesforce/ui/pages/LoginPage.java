package org.fundacionjala.salesforce.ui.pages;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "Login")
    WebElement loginBtn;

    private void setUsernameField(final String username) {
        GuiInteractioner.setInputText(usernameField, username);
    }

    private void setPasswordField(final String username) {
        GuiInteractioner.setInputText(passwordField, username);
    }

    private void clickLoginBtn() {
        GuiInteractioner.clickWebElement(loginBtn);
    }

    public void login(final String username, final String password) {
        setUsernameField(username);
        setPasswordField(password);
        clickLoginBtn();
    }
}
