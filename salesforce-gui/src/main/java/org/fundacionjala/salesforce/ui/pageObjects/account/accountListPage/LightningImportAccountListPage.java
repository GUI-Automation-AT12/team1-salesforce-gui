package org.fundacionjala.salesforce.ui.pageObjects.account.accountListPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.utils.PageTransporter;
import org.fundacionjala.salesforce.utils.TableUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public class LightningImportAccountListPage extends AbstractAccountListPage {

    private By dropDownButton = By.xpath("//div[@data-aura-class='forceListViewPicker']");
    private By newThisWeek = By.xpath("//li[contains(@class,'forceVirtualAutocompleteMenuOption')]"
            + "//*[contains(text(), 'New This')]");
    private By billingState = By.xpath("//span[contains(text(), 'Billing State')]");
    private By scroll = By.xpath("//div[@class='listViewContent slds-table--header-fixed_container']/div[1]");
    private By headerTable = By.xpath("//table[@data-aura-class='uiVirtualDataTable']/thead//span[@title]");
    private By contentTable = By.xpath("//table[@data-aura-class='uiVirtualDataTable']"
            + "/tbody/tr/*[contains(@data-aura-class,'forceInlineEditCell')]");
    private String rowId = "(//a[@data-refid])[%d]";


    /**
     * [sl] Return a list with all data of the table.
     *
     * @return a list of maps with the data
     * @throws MalformedURLException
     */
    public List<Map<String, String>> getTableNewThisWeek() throws MalformedURLException {
        PageTransporter.navigateToPage("ACCOUNTS");
        getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(dropDownButton));

        GuiInteractioner.clickWebElement(dropDownButton);
        GuiInteractioner.clickWebElement(newThisWeek);

        getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(billingState));
        GuiInteractioner.loadDynamicTable(scroll);

        List<Map<String, String>> elements = TableUtils.parseDynamicTableAsList(headerTable, contentTable,
                rowId, "data-recordid");
        return elements;
    }
}
