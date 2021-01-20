package org.fundacionjala.salesforce.ui.PersonalInformation;

import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.fundacionjala.salesforce.ui.PersonalInformation.AbstractEditPersonalInformationPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

public class ClassicEditPersonalInformationPage extends AbstractEditPersonalInformationPage {

    @FindBy(xpath = "//input [@title='Save']")
    private WebElement saveBtn;

    /**
     * [SL] This abstract method update the data of the entities and the web.
     *
     * @param formFields is a map with the all data to update
     */
    @Override
    public void update(final Map<String, String> formFields) {
        setInformationToEntities(formFields);
        changeDataFormWeb();
        saveData();
    }

    /**
     * [SL] Returns a Map with all data of the form web.
     *
     * @return a Map with the information required
     */
    @Override
    public Map<String, String> getWebFromAsMap() {
        return getWebForm();
    }

    /**
     * [SL] Method wait to load BoardPage.
     */
    @Override
    protected void waitLoadPage() {
        WebDriverManager.getInstance().getWebDriverWait().until(ExpectedConditions.visibilityOf(saveBtn));
    }
}
