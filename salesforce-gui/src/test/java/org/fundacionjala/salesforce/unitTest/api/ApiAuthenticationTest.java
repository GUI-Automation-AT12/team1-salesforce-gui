package org.fundacionjala.salesforce.unitTest.api;

import io.restassured.response.Response;
import org.fundacionjala.core.api.client.RequestManager;
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
        User user = new User("3MVG9kBt168mda__Ksae0V6SChML_UMpkbG9MEr_zbEtJglmauhYjjCUKVyGYgoJzSX1YJWo3p0Gw_ziNXSq1",
                "61FBD3DEF612DCC0C4D166696E12411AE7EC0048624610A5E2493DAF30BDAE6A",
                "mirko@fjala.com", "72440446mfrr");
        RequestManager.setRequestSpec(ApiAuthenticator.getLoggedReqSpec(user));
        Response response = RequestManager.get("/Account");
        Assert.assertEquals(response.getStatusCode(), OK_STATUS_CODE, "The response did not get '200 OK' status code");
    }
}
