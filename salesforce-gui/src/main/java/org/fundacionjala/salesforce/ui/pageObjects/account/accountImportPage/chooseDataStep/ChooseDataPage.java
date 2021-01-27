package org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.chooseDataStep;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class ChooseDataPage extends BasePage {

    private By accountAndImportElement = By.xpath("//a[@data-interactive-lib-uid='9']");
    private By newRecordsBy = new By.ByLinkText("Add new records");
    private By dropDownMatch = By.xpath("//div [@class='dataImporterDiAdvancedAccountContactOptions'][1]/select");
    private String uploadOption = "//div [@data-aura-class='dataImporterDiCsvSelectionActivity'][%d]/a";
    private By chooseFile = By.xpath("//input[@name='file']");
    private By nextButton = By.xpath("//div[@data-aura-class='dataImporterDiButtonBar']/a[3]");

    /**
     * Clicks in "Account and Page" option.
     */
    public void clickAccountsAndContactsOption() {
        GuiInteractioner.clickWebElement(accountAndImportElement);
    }


    /**
     * Clicks in "New Records" option.
     */
    public void clickAddNewRecordsOption() {
        GuiInteractioner.clickWebElement(newRecordsBy);
    }

    /**
     * Selects Email option of the dropDown.
     *
     * @param fieldEmail
     */
    public void selectMatchContact(final String fieldEmail) {
        Select dropDown = new Select(getDriver().findElement(dropDownMatch));
        dropDown.selectByValue(fieldEmail);
    }

    /**
     * Selects CSV option of the dropDown.
     *
     * @param option
     */
    public void selectOption(final String option) {
        int uploadPositionOption;
        switch (option) {
            case "CSV":
                uploadPositionOption = UploadOptions.CSV_OPTION.ordinal();
                break;
            case "Outlook CSV":
                uploadPositionOption = UploadOptions.OUTLOOK_OPTION.ordinal();
                break;
            default:
                uploadPositionOption = UploadOptions.CSV_OPTION.ordinal();
                break;
        }
        GuiInteractioner.clickWebElement(By.xpath(String.format(uploadOption, uploadPositionOption + 1)));
    }

    /**
     * [SL] Selects a File.
     *
     * @param nameFile
     */
    public void selectFile(final String nameFile) {
        String csvFile = new File("src/test/resources/files/csv/" + nameFile).getAbsoluteFile().toString();
        GuiInteractioner.setInputText(chooseFile, csvFile);
    }

    /**
     * Clicks in "Next" button.
     */
    public void clickNextButton() {
        GuiInteractioner.clickWebElement(nextButton);
    }

    @Override
    protected void waitLoadPage() {

    }
}
