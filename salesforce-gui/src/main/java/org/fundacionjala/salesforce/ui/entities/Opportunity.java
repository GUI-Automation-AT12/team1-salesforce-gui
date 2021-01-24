package org.fundacionjala.salesforce.ui.entities;

import org.fundacionjala.salesforce.constants.OpportunityConstants;
import org.fundacionjala.salesforce.utils.DateConverter;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * [MR] Class that represent Opportunity records in salesforce.
 */
public class Opportunity {
    private String id;
    private String name;
    private Account account;
    private Date closeDate;
    private String stage;
    private Set<String> updatedFields;

    /**
     * Gets the Opportunity id.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the Opportunity's id.
     * @param opportunityId
     */
    public void setId(final String opportunityId) {
        this.id = opportunityId;
    }

    /**
     * Gets the Opportunity name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Opportunity's name.
     * @param opportunityName
     */
    public void setName(final String opportunityName) {
        this.name = opportunityName;
    }

    /**
     * Gets the Opportunity account.
     * @return account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the Opportunity's Account searching in previous existent account.
     * @param opportunityAccount name
     */
    public void setAccount(final String opportunityAccount) {
        try {
            List<Account> accountList = EntitiesParser.getAccountListFromJson();
            for (Account accountFromList : accountList) {
                if (accountFromList.getName().equals(opportunityAccount)) {
                    this.account = accountFromList;
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Gets the Opportunity closeDate.
     * @return closeDate
     */
    public Date getCloseDate() {
        return closeDate;
    }

    /**
     * Sets the Opportunity's closeDate providing a String to parse to a valid date.
     * @param opportunityCloseDate
     */
    public void setCloseDate(final String opportunityCloseDate) {
        this.closeDate = DateConverter.convertTextToDate(opportunityCloseDate);
    }

    /**
     * Gets the Opportunity stage.
     * @return stage
     */
    public String getStage() {
        return stage;
    }

    /**
     * Sets Opportunity's stage.
     * @param opportunityStage
     */
    public void setStage(final String opportunityStage) {
        this.stage = opportunityStage;
    }

    /**
     * Gets Opportunity's updatedFields.
     *
     * @return updatedFields
     */
    public Set<String> getUpdatedFields() {
        return updatedFields;
    }

    /**
     * Sets Opportunity's updatedFields.
     *
     * @param opportunityUpdatedFields to set
     */
    public void setUpdatedFields(final Set<String> opportunityUpdatedFields) {
        this.updatedFields = opportunityUpdatedFields;
    }

    private HashMap<String, Runnable> composeStrategySetterMap(final Map<String, String> opportunityInfo) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();
        strategyMap.put(OpportunityConstants.NAME_KEY, () ->
                setName(opportunityInfo.get(OpportunityConstants.NAME_KEY)));
        strategyMap.put(OpportunityConstants.ACCOUNT_KEY, () ->
                setAccount(opportunityInfo.get(OpportunityConstants.ACCOUNT_KEY)));
        strategyMap.put(OpportunityConstants.CLOSE_DATE_KEY, () ->
                setCloseDate(opportunityInfo.get(OpportunityConstants.CLOSE_DATE_KEY)));
        strategyMap.put(OpportunityConstants.STAGE_KEY, () ->
                setStage(opportunityInfo.get(OpportunityConstants.STAGE_KEY)));
        return strategyMap;
    }

    /**
     * Set all information stored for an Opportunity as a map.
     * @param opportunityInfo to set
     */
    public void setInformation(final Map opportunityInfo) {
        HashMap<String, Runnable> strategyMap = composeStrategySetterMap(opportunityInfo);
        opportunityInfo.keySet().forEach(key -> strategyMap.get(key).run());
    }

    private HashMap<String, Supplier<String>> composeStrategyGetterMap() {
        HashMap<String, Supplier<String>> strategyMap = new HashMap<>();
        strategyMap.put(OpportunityConstants.NAME_KEY, () -> getName());
        strategyMap.put(OpportunityConstants.ACCOUNT_KEY, () -> getAccount().getName());
        strategyMap.put(OpportunityConstants.CLOSE_DATE_KEY, () ->
                DateConverter.convertDateToFormattedText(getCloseDate()));
        strategyMap.put(OpportunityConstants.STAGE_KEY, () -> getStage());
        return strategyMap;
    }

    /**
     * Gets Opportunity's info of updated fields as Map.
     *
     * @return accountInfoMap
     */
    public Map<String, String> getOpportunityInfo() {
        Map opportunityInfoMap = new HashMap<String, String>();
        updatedFields.forEach(field -> opportunityInfoMap.put(field, composeStrategyGetterMap().get(field).get()));
        return opportunityInfoMap;
    }

    /**
     * Gets Opportunity's info specific fields as Map.
     *
     * @param fields to get info
     * @return accountInfoMap
     */
    public Map<String, String> getOpportunityInfo(final Set<String> fields) {
        Map opportunityInfoMap = new HashMap<String, String>();
        fields.forEach(field -> opportunityInfoMap.put(field, composeStrategyGetterMap().get(field).get()));
        return opportunityInfoMap;
    }
}
