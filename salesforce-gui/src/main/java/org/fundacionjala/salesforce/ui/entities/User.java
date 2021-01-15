package org.fundacionjala.salesforce.ui.entities;

/**
 * [MR] Entity that represents a USer in Salesforce.
 */
public class User {

    private String clientId;
    private String clientSecret;
    private String username;
    private String password;

    /**
     * Constructor for User class with initial information.
     * @param userClientId
     * @param userClientSecret
     * @param userUsername
     * @param userPassword
     */
    public User(final String userClientId, final String userClientSecret,
                final String userUsername, final String userPassword) {
        this.clientId = userClientId;
        this.clientSecret = userClientSecret;
        this.username = userUsername;
        this.password = userPassword;
    }

    /**
     * Get User's clientID.
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
}
