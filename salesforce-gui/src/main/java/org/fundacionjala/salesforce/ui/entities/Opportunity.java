package org.fundacionjala.salesforce.ui.entities;

import org.fundacionjala.salesforce.utils.DateConverter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Opportunity {
    private String id;
    private String name;
    private Account account;
    private Date closeDate;
    private String stage;

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
}
