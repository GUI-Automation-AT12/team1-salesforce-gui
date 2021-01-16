package org.fundacionjala.salesforce.ui.entities;

/**
 * [MR] Entity that represents a USer in Salesforce.
 */
public class User {

    private String alias;
    private String clientId;
    private String clientSecret;
    private String username;
    private String password;

    /**
     * Get User's clientID.
     *
     * @return clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Get User's clientSecret.
     * @return clientSecret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * Get User's username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get User's password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get User's alias.
     * @return alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Set User's clientId.
     * @param userClientId
     */
    public void setClientId(final String userClientId) {
        this.clientId = userClientId;
    }

    /**
     * Set User's clientSecret.
     * @param userClientSecret
     */
    public void setClientSecret(final String userClientSecret) {
        this.clientSecret = userClientSecret;
    }

    /**
     * Set User's username.
     * @param userUsername
     */
    public void setUsername(final String userUsername) {
        this.username = userUsername;
    }

    /**
     * Set User's password.
     * @param userPassword
     */
    public void setPassword(final String userPassword) {
        this.password = userPassword;
    }

    /**
     * Set User's alias.
     * @param userAlias
     */
    public void setAlias(final String userAlias) {
        this.alias = userAlias;
    }
}
