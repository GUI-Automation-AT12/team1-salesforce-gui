package org.fundacionjala.salesforce.ui.pageObjects.account.accountImportPage.editMapping;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.pageObjects.commonPages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [SL] This class EditMappingPage object.
 */
public class EditMappingPage extends BasePage {

    private By tableRows = By.xpath("//table[@class='list-view list-view-field-mapping']/tbody/tr/*[position()>1][1]");
    private By tableTitle = By.xpath("//table[@class='list-view list-view-field-mapping']/tbody/tr/*[position()>1][2]");
    private String mappedField = "//div[@class='block second-block']//*[text()='%s']/child::span";
    private By nextButton = By.xpath("//div[@data-aura-class='dataImporterDiButtonBar']/a[3]");
    private By okCongratulationBtn = By.xpath("//a[@data-interactive-lib-uid='8']");

    /**
     * Gets the data of the table of the Choose Edit Mapping step.
     *
     * @return a map with the all data of the table
     */
    public Map<String, String> chooseEditMapping() {
        Map<String, String> table = new HashMap<>();

        List<WebElement> itemsSalesforceList = getDriver().findElements(tableRows);
        List<WebElement> itemsSalesforceCSVHeaderList = getDriver().findElements(tableTitle);

        if (itemsSalesforceList.size() != itemsSalesforceCSVHeaderList.size()) {
            return null;
        }

        for (int i = 0; i < itemsSalesforceList.size(); i++) {
            String key = itemsSalesforceList.get(i).getText();
            String value = itemsSalesforceCSVHeaderList.get(i).getText();
            table.put(key, value);
        }
        return table;
    }

    /**
     * Gets a number with the the Mapped fields found.
     *
     * @return a number with the Mapped fields
     */

    public int getMappedValue() {
        By getMappedFields = By.xpath(String.format(mappedField, "Mapped fields"));
        return Integer.parseInt(GuiInteractioner.getTextFromWebElement(getMappedFields));
    }

    /**
     * Gets a number with the the Unmapped fields found.
     *
     * @return a number with the unmapped fields
     */
    public int getUnmappedValue() {
        By getMappedFields = By.xpath(String.format(mappedField, "Unmapped fields"));
        return Integer.parseInt(GuiInteractioner.getTextFromWebElement(getMappedFields));
    }

    /**
     * Clicks in the Next Button.
     */
    public void clickNextButton() {
        GuiInteractioner.clickWebElement(nextButton);
    }

    /**
     * Clicks in the Ok option of the popup.
     */
    public void clickEditMappingNextPage() {
        GuiInteractioner.clickWebElement(nextButton);
        GuiInteractioner.clickWebElement(okCongratulationBtn);
    }

    @Override
    protected void waitLoadPage() {

    }
}
