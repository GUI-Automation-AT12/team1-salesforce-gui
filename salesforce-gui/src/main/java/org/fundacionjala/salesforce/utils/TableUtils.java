package org.fundacionjala.salesforce.utils;

import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * [SL] This CLass contains utilities for process a table.
 */
public final class TableUtils {

    /**
     * Constructor.
     */
    private TableUtils() {

    }

    /**
     * [SL] Converts the content of a table to list and every element contain a map key=header, value=text.
     *
     * @param headerTable  a By of the header of table
     * @param contentTable a By of the content of table
     * @param idRow        a identification unique of every row for example the id
     * @param attribute    an attribute for get the id
     * @return a List of all elements of table
     */
    public static List<Map<String, String>> parseDynamicTableAsList(final By headerTable, final By contentTable,
                                                                    final String idRow, final String attribute) {
        //Wait to render the new rows of the table
        WebDriverManager.getInstance().getWebDriverWait()
                .until(ExpectedConditions.visibilityOfElementLocated(contentTable));

        List<String> headerRows = new ArrayList<>();
        WebDriverManager.getInstance().getWebDriver().findElements(headerTable).stream().forEach(element -> {
            if (!element.getText().isEmpty()) {
                headerRows.add(element.getText());
            }
        });
        boolean customId = false;
        if (!idRow.isEmpty()) {
            customId = true;
        }

        List<Map<String, String>> elements = new ArrayList<>();
        Map<String, String> mapElements = new LinkedHashMap<String, String>();
        int count = 0;
        int row = 1;
        boolean firstData = true;
        for (WebElement element : WebDriverManager.getInstance().getWebDriver().findElements(contentTable)) {
            String key = headerRows.get(count % headerRows.size());
            String value = element.getText();
            mapElements.put(key, value);
            if (firstData && customId) {
                String id = element.findElement(By.xpath(String.format(idRow, row))).getAttribute(attribute);
                mapElements.put("data-recordid", id);
                firstData = false;
            }
            if (count % headerRows.size() == headerRows.size() - 1) {
                elements.add(mapElements);
                row++;
                mapElements = new LinkedHashMap<String, String>();
                firstData = true;
            }
            count++;
        }
        elements.add(mapElements);
        return elements;
    }
}
