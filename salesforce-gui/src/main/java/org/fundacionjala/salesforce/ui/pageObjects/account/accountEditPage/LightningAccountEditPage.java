package org.fundacionjala.salesforce.ui.pageObjects.account.accountEditPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.utils.DateConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


/**
 * [SL] LightningAccountEditPage.
 */
public class LightningAccountEditPage extends AbstractAccountEditPage {

    private By slaExpirationDate = By.xpath("//input[@name='SLAExpirationDate__c']");
    private By saveButton = By.xpath("//button[@title='Save']");

    private void clickSLAExpirationDate() {
        GuiInteractioner.clickWebElement(slaExpirationDate);
    }

    private void sendSLAExpirationDate(final String date) {
        GuiInteractioner.setInputText(slaExpirationDate, date);
    }

    private void clickSave() {
        GuiInteractioner.clickWebElement(saveButton);
    }

    /**
     * [SL] Changes the sla expiration date.
     *
     * @param date a constant what represent the date
     */
    @Override
    public void changeSLAExpirationDate(final String date) {
        String dateFormat = DateConverter.convertDateToFormattedText(DateConverter.convertTextToDate(date));
        clickSLAExpirationDate();
        sendSLAExpirationDate(dateFormat);
        getDriver().findElement(slaExpirationDate).sendKeys(Keys.ENTER);
        clickSave();
    }
}
