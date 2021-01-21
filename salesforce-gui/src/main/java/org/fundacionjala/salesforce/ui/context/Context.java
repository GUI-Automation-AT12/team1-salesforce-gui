package org.fundacionjala.salesforce.ui.context;

import io.restassured.specification.RequestSpecification;
import org.fundacionjala.core.api.client.RequestManager;
import org.fundacionjala.salesforce.ui.entities.EntitiesParser;
import org.fundacionjala.salesforce.ui.entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * [MR] Context class to share between Step Definitions.
 */
public class Context {

    private List<User> usersList;

    /**
     * Constructor for Context class.
     */
    public Context() {
        this.usersList = new ArrayList<>();
    }

    private void setUsersListFromJson() throws IOException {
        this.usersList = EntitiesParser.getUsersListFromJson();
    }

    /**
     * Searches for a specific User in userList by a provided alias.
     *
     * @param alias provided alias
     * @return User if the alias matches, otherwise return null.
     */
    public User getUserByAlias(final String alias) throws IOException {
        if (this.usersList.size() == 0) {
            setUsersListFromJson();
        }
        for (User user : this.usersList) {
            if (alias.equals(user.getAlias())) {
                return user;
            }
        }
        return null;
    }
}
