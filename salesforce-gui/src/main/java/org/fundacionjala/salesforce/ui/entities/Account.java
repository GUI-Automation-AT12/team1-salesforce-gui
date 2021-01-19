package org.fundacionjala.salesforce.ui.entities;

import org.fundacionjala.salesforce.constants.AccountConstants;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account {
    private String name;
    private Account parentAccount;
    private String site;
    private String rating;
    private String billingCity;
    private String phone;
    private String description;

    /**
     * Gets Account's name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets Account's name
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
}
