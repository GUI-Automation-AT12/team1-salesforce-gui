package org.fundacionjala.salesforce.ui.skins.abstractPage.personalInformation;

import org.fundacionjala.core.selenium.interaction.GuiInteractioner;
import org.fundacionjala.salesforce.ui.commonPages.BasePage;
import org.fundacionjala.salesforce.ui.skins.constants.personalInformation.Constants;
import org.fundacionjala.salesforce.ui.entities.PersonalInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public abstract class AbstractEditPersonalInformationPage extends BasePage {

    private String baseNameEditForm = "PersonalInformationSetup:editPage:pageBlock:%s";
    private List<String> listFields;
    private PersonalInformation personalInformation;

    @FindBy(xpath = "//input [@title='Save']")
    private WebElement saveBtn;

    /**
     * Constructor.
     */
    public AbstractEditPersonalInformationPage() {
        personalInformation = new PersonalInformation();
        initListFieldsToUpdate();
    }

    /**
     * This method initialize the fields to update.
     */
    private void initListFieldsToUpdate() {
        listFields = new ArrayList<>();
        listFields.add(Constants.FIRST_NAME);
        listFields.add(Constants.LAST_NAME);
        listFields.add(Constants.ALIAS);
    }

    /**
     * This abstract method update the data of the entities and the web.
     *
     * @param formFields is a map with the all data to update
     */
    public abstract void update(Map<String, String> formFields);

    /**
     * Sets datatable to entities.
     *
     * @param formFields map with the all data to update
     */
    protected void setInformationToEntities(final Map<String, String> formFields) {
        HashMap<String, Runnable> strategyMap = composeStrategySetMap(formFields);
        formFields.keySet().forEach(key -> strategyMap.get(key).run());
    }

    /**
     * Returns a map with the methods for set personal information.
     *
     * @param formFields
     * @return a map with the methods.
     */
    private HashMap<String, Runnable> composeStrategySetMap(final Map<String, String> formFields) {
        HashMap<String, Runnable> fields = new HashMap<>();
        fields.put(Constants.FIRST_NAME, () -> personalInformation.setFirstName(formFields.get(Constants.FIRST_NAME)));
        fields.put(Constants.LAST_NAME, () -> personalInformation.setLastName(formFields.get(Constants.LAST_NAME)));
        fields.put(Constants.ALIAS, () -> personalInformation.setAlias(formFields.get(Constants.ALIAS)));
        return fields;
    }

    /**
     * This method change the fields of the web for the datatable.
     */
    protected void changeDataFormWeb() {
        HashMap<String, Supplier<String>> fields = composeStrategyGetMap();
        listFields.forEach(field -> {
            By id = By.id(String.format(baseNameEditForm, field));
            GuiInteractioner.setInputText(id, fields.get(field).get());
        });
    }

    /**
     * Returns a map with the methods for get personal information.
     *
     * @return a map with the methods.
     */
    private HashMap<String, Supplier<String>> composeStrategyGetMap() {
        HashMap<String, Supplier<String>> fields = new HashMap<>();
        fields.put(Constants.FIRST_NAME, () -> personalInformation.getFirstName());
        fields.put(Constants.LAST_NAME, () -> personalInformation.getLastName());
        fields.put(Constants.ALIAS, () -> personalInformation.getAlias());
        return fields;
    }

    /**
     * Returns a Map with all data of the form web.
     *
     * @return a Map with the information required
     */
    protected Map<String, String> getWebForm() {
        Map<String, String> fields = new HashMap<>();
        listFields.forEach(field -> {
            By id = By.id(String.format(baseNameEditForm, field));
            fields.put(field, GuiInteractioner.getValueOfWebElement(id));
        });
        return fields;
    }

    /**
     * Returns a Map with all data of the form web.
     *
     * @return a Map with the information required
     */
    public abstract Map<String, String> getWebFromAsMap();

    /**
     * Gets personal information as map.
     *
     * @return a map of the entities of the PersonalInformation
     */
    public Map<String, String> getPersonalInformationAsMap() {
        Map<String, String> data = new HashMap<>();
        HashMap<String, Supplier<String>> dataEntities = composeStrategyGetMap();
        listFields.forEach(field -> {
            data.put(field, dataEntities.get(field).get());
        });
        return data;
    }

    /**
     * Clicks in the button save.
     */
    protected void saveData() {
        GuiInteractioner.clickWebElement(saveBtn);
    }
}
