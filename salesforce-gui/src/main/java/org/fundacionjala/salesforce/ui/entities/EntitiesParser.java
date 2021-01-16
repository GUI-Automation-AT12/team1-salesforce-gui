package org.fundacionjala.salesforce.ui.entities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * [MR] Class in charged to parse json files to Entity objects.
 */
public final class EntitiesParser {

    private static final String JSON_FILES_PATH = "src/main/java/org/fundacionjala/salesforce/config/jsonFiles/";
    private static final String USERS_JSON_FILE_NAME = "InitialUsers.json";

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static List<User> userList = null;

    /**
     * Get a list of Users from a from DefaultUsers json file.
     * @return userList
     */
    public static List<User> getUsersListFromJson() throws IOException {
        if (userList == null) {
            userList = objectMapper.readValue(
                    new File(JSON_FILES_PATH + USERS_JSON_FILE_NAME), new TypeReference<>() { });
        }
        return userList;
    }
}
