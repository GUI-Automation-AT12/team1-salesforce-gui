package org.fundacionjala.salesforce.ui.entities;

/**
 * [SL] Entity that represents a Personal Information in Salesforce.
 */
public class PersonalInformation {

    private String firstName;
    private String lastName;
    private String alias;

    /**
     * [SL] Gets firstName.
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * [SL] Sets fistName.
     *
     * @param accountFirstName
     */
    public void setFirstName(final String accountFirstName) {
        this.firstName = accountFirstName;
    }

    /**
     * [SL] Gets lastName.
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets lastName.
     *
     * @param accountLastName
     */
    public void setLastName(final String accountLastName) {
        this.lastName = accountLastName;
    }

    /**
     * Gets alias.
     *
     * @return alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets alias.
     *
     * @param accountAlias
     */
    public void setAlias(final String accountAlias) {
        this.alias = accountAlias;
    }
}
