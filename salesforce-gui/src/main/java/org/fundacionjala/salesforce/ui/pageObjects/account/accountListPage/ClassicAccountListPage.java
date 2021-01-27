package org.fundacionjala.salesforce.ui.pageObjects.account.accountListPage;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.utils.PageTransporter;
import org.fundacionjala.salesforce.utils.TableUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public class ClassicAccountListPage extends AbstractAccountListPage {

    private By dropDownButton = By.xpath("//form[@id='filter_element']//select");
    private By newThisWeek = By.xpath("//form[@id='filter_element']//select//*[contains(text(), 'New This')]");
    private By billingState = By.xpath("//div[contains(@title, 'Billing State')]");
    private By goButton = By.xpath("//form[@id='filter_element']//input");
    protected By headerTable = By.xpath("(//table/thead/tr//div[@title])[position() > 2]");
    protected By contentTable = By.xpath("//table[@class='x-grid3-row-table']/tbody/tr/td[position() > 3]");
    protected String rowId = "(//table[@class='x-grid3-row-table']/tbody/tr/td/div/input[@id])[%d]";

    /**
     * [sl] Return a list with all data of the table.
     *
     * @return a list of maps with the data
     * @throws MalformedURLException
     */
    @Override
    public List<Map<String, String>> getTableNewThisWeek() throws MalformedURLException {
        PageTransporter.navigateToPage("ACCOUNTS");

        GuiInteractioner.clickWebElement(dropDownButton);
        GuiInteractioner.clickWebElement(newThisWeek);
        GuiInteractioner.clickWebElement(goButton);
        getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(billingState));

        List<Map<String, String>> elements = TableUtils.parseDynamicTableAsList(headerTable, contentTable,
                rowId, "id");
        return elements;
    }
}
