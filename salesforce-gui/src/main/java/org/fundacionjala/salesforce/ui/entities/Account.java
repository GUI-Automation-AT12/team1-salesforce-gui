package org.fundacionjala.salesforce.ui.entities;

import org.fundacionjala.salesforce.constants.AccountConstants;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * [MR] Class that represent Account records in salesforce.
 */
public class Account {

    private String id;
    private String name;
    private Account parentAccount;
    private String site;
    private String rating;
    private String billingCity;
    private String phone;
    private String description;
    private Set<String> updatedFields;

    /**
     * Gets Account's id.
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets Account's id.
     *
     * @param accountId
     */
    public void setId(final String accountId) {
        this.id = accountId;
    }

    /**
     * Gets Account's name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets Account's name.
     *
     * @param accountName to set
     */
    public void setName(final String accountName) {
        this.name = accountName;
    }

    /**
     * Gets Account's parent Account.
     *
     * @return parentAccount
     */
    public Account getParentAccount() {
        return parentAccount;
    }

    /**
     * Sets Account's parent account.
     *
     * @param accountParentAccount to set
     */
    public void setParentAccount(final String accountParentAccount) {
        try {
            List<Account> accountList = EntitiesParser.getAccountListFromJson();
            for (Account account : accountList) {
                if (account.getName().equals(accountParentAccount)) {
                    this.parentAccount = account;
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Gets Account's site.
     *
     * @return site
     */
    public String getSite() {
        return site;
    }

    /**
     * Sets Account's site.
     *
     * @param accountSite to set
     */
    public void setSite(final String accountSite) {
        this.site = accountSite;
    }

    /**
     * Gets Account's rating.
     *
     * @return rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * Sets Account's rating.
     *
     * @param accountRating to set
     */
    public void setRating(final String accountRating) {
        this.rating = accountRating;
    }

    /**
     * Gets Account's billing city.
     *
     * @return billingCity
     */
    public String getBillingCity() {
        return billingCity;
    }

    /**
     * Gets Account's billing city.
     *
     * @param accountBillingCity to set
     */
    public void setBillingCity(final String accountBillingCity) {
        this.billingCity = accountBillingCity;
    }

    /**
     * Gets Account's phone.
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets Account's phone.
     *
     * @param accountPhone to set
     */
    public void setPhone(final String accountPhone) {
        this.phone = accountPhone;
    }

    /**
     * Gets Account's description.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets Account's description.
     *
     * @param accountDescription to set
     */
    public void setDescription(final String accountDescription) {
        this.description = accountDescription;
    }

    /**
     * Gets Account's updatedFields.
     *
     * @return updatedFields
     */
    public Set<String> getUpdatedFields() {
        return updatedFields;
    }

    /**
     * Sets Account's updatedFields.
     *
     * @param accountUpdatedFields to set
     */
    public void setUpdatedFields(final Set<String> accountUpdatedFields) {
        this.updatedFields = accountUpdatedFields;
    }

    private HashMap<String, Runnable> composeMapStrategy(final Map<String, String> accountInfo) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();
        strategyMap.put(AccountConstants.NAME_KEY, () -> setName(accountInfo.get(AccountConstants.NAME_KEY)));
        strategyMap.put(AccountConstants.PARENT_ACCOUNT_KEY, () ->
                setParentAccount(accountInfo.get(AccountConstants.PARENT_ACCOUNT_KEY)));
        strategyMap.put(AccountConstants.SITE_KEY, () -> setSite(accountInfo.get(AccountConstants.SITE_KEY)));
        strategyMap.put(AccountConstants.PHONE_KEY, () -> setPhone(accountInfo.get(AccountConstants.PHONE_KEY)));
        strategyMap.put(AccountConstants.BILLING_CITY_KEY, () ->
                setBillingCity(accountInfo.get(AccountConstants.BILLING_CITY_KEY)));
        strategyMap.put(AccountConstants.RATING_KEY, () -> setRating(accountInfo.get(AccountConstants.RATING_KEY)));
        strategyMap.put(AccountConstants.DESCRIPTION_KEY, () ->
                setDescription(accountInfo.get(AccountConstants.DESCRIPTION_KEY)));
        return strategyMap;
    }

    /**
     * Set all information stored for an Account as a map.
     * @param accountInfo
     */
    public void setInformation(final Map accountInfo) {
        HashMap<String, Runnable> strategyMap = composeMapStrategy(accountInfo);
        accountInfo.keySet().forEach(key -> strategyMap.get(key).run());
    }

    private HashMap<String, Supplier<String>> composeStrategyGetterMap() {
        HashMap<String, Supplier<String>> strategyMap = new HashMap<>();
        strategyMap.put(AccountConstants.NAME_KEY, () -> getName());
        strategyMap.put(AccountConstants.PARENT_ACCOUNT_KEY, () -> getParentAccount().getName());
        strategyMap.put(AccountConstants.SITE_KEY, () -> getSite());
        strategyMap.put(AccountConstants.PHONE_KEY, () -> getPhone());
        strategyMap.put(AccountConstants.BILLING_CITY_KEY, () -> getBillingCity());
        strategyMap.put(AccountConstants.RATING_KEY, () -> getRating());
        strategyMap.put(AccountConstants.DESCRIPTION_KEY, () -> getDescription());
        return strategyMap;
    }

    /**
     * Gets Account's info of updated fields as Map.
     *
     * @return accountInfoMap
     */
    public Map<String, String> getAccountInfo() {
        Map accountInfoMap = new HashMap<String, String>();
        updatedFields.forEach(field -> accountInfoMap.put(field, composeStrategyGetterMap().get(field).get()));
        return accountInfoMap;
    }

    /**
     * Gets Account's info specific fields as Map.
     *
     * @param fields to get info
     * @return accountInfoMap
     */
    public Map<String, String> getAccountInfo(final Set<String> fields) {
        Map accountInfoMap = new HashMap<String, String>();
        fields.forEach(field -> accountInfoMap.put(field, composeStrategyGetterMap().get(field).get()));
        return accountInfoMap;
    }
}
