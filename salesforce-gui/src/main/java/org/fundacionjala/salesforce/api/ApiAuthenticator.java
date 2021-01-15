package org.fundacionjala.salesforce.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.entity.ContentType;
import org.fundacionjala.salesforce.config.SalesforceProperties;
import org.fundacionjala.salesforce.ui.entities.User;

public class ApiAuthenticator {

    private static final String GRANT_TYPE_KEY = "grant_type";
    private static final String GRANT_TYPE_VAL = "password";
    private static final String CLIENT_ID_KEY = "client_id";
    private static final String CLIENT_SECRET_KEY = "client_secret";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";

    /**
     * It try to get the credentials from salesforce API, sending a request for that purpose.
     * @param user with the information necessary to log in.
     * @return the authentication response from salesforce.
     */
    private static Response sendApiAuthenticationRequest(final User user) {
        RestAssured.baseURI = SalesforceProperties.getInstance().getApiLoginUrl();
        Response response = RestAssured.given()
                .param(GRANT_TYPE_KEY, GRANT_TYPE_VAL)
                .param(CLIENT_ID_KEY, user.getClientId())
                .param(CLIENT_SECRET_KEY, user.getClientSecret())
                .param(USERNAME_KEY, user.getUsername())
                .param(PASSWORD_KEY, user.getPassword())
                .when().post();
        return response;
    }

    /**
     * Get requestSpecification and set main headers to build the request to send.
     * @return the request built.
     */
    public static RequestSpecification getLoggedReqSpec(final User user) {
        RequestSpecification request = RestAssured.given();
        Response response = sendApiAuthenticationRequest(user);
        String instanceUrl = response.jsonPath().getString("instance_url");
        String baseUrl = instanceUrl + SalesforceProperties.getInstance().getBaseApiUrl();
        String accessToken =  response.jsonPath().getString("access_token");
        request.baseUri(baseUrl);
        request.auth().oauth2(accessToken);
        request.contentType(ContentType.APPLICATION_JSON.toString());
        return request;
    }
}
