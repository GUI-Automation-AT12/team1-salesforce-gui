package org.fundacionjala.salesforce.unitTest.api;

import io.restassured.response.Response;
import org.fundacionjala.core.api.client.RequestManager;
import org.fundacionjala.salesforce.api.ApiAuthenticator;
import org.fundacionjala.salesforce.ui.entities.User;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * [MR] Testing api authentication and sending get request without password encoding.
 */

public class ApiAuthenticationTest {

    static final int OK_STATUS_CODE = 200;

    /**
     * Test that use the Api Authenticator to get a response from Salesforce API.
     */
    @Test
    public void getting200StatusCodeFromSalesforceApi() {
        User user = new User();
        user.setAlias("User Alias");
        user.setClientId("M01WRzlrQnQxNjhtZGFfX0tzYWUwVjZTQ2hNTF9VTXBrYkc5TU"
                + "VyX3piRXRKZ2xtYXVoWWpqQ1VLVnlHWWdvSnpTWDFZSldvM3AwR3dfemlOWFNxMQ");
        user.setClientSecret("NjFGQkQzREVGNjEyRENDMEM0RDE2NjY5NkUxMjQxMUFFN0VDMDA0ODYyNDYxMEE1RTI0OTNEQUYzMEJEQUU2QQ");
        user.setUsername("mirko@fjala.com");
        user.setPassword("NzI0NDA0NDZtZnJy");
        RequestManager.setRequestSpec(ApiAuthenticator.getLoggedReqSpec(user));
        Response response = RequestManager.get("/Account");
        Assert.assertEquals(response.getStatusCode(), OK_STATUS_CODE, "The response did not get '200 OK' status code");
    }
}
