package org.fundacionjala.salesforce.ui.entities;

/**
 * [MR] Entity that represents a USer in Salesforce.
 */
public class User {

    private String clientId;
    private String clientSecret;
    private String username;
    private String password;
    private String alias;

    /**
     * Default Constructor for Project Entity class.
     */
    public User() {
    }

    /**
     * Constructor for User class with initial information.
     * @param userClientId
     * @param userClientSecret
     * @param userUsername
     * @param userPassword
     */
    public User(final String userAlias, final String userClientId, final String userClientSecret,
                final String userUsername, final String userPassword) {
        this.alias = userAlias;
        this.clientId = userClientId;
        this.clientSecret = userClientSecret;
        this.username = userUsername;
        this.password = userPassword;
    }

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
     *
     * @return clientSecret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * Get User's username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get User's password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get User's alias.
     *
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
        this.clientId = clientId;
    }

    /**
     * Set User's clientSecret.
     * @param userClientSecret
     */
    public void setClientSecret(final String userClientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * Set User's username.
     * @param userUsername
     */
    public void setUsername(final String userUsername) {
        this.username = username;
    }

    /**
     * Set User's password.
     * @param userPassword
     */
    public void setPassword(final String userPassword) {
        this.password = password;
    }

    /**
     * Set User's alias.
     * @param userAlias
     */
    public void setAlias(final String userAlias) {
        this.alias = alias;
    }
}
