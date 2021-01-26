package org.fundacionjala.salesforce.cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.fundacionjala.core.api.client.RequestManager;
import org.fundacionjala.core.selenium.interaction.WebDriverManager;
import org.fundacionjala.salesforce.api.ApiAuthenticator;
import org.fundacionjala.salesforce.ui.context.Context;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * [MR] Hooks for scenarios related to Accounts.
 */
public class AccountHooks {

    //Context
    private final Context context;
    private List<String> preconditionAccountsList = new ArrayList<>();
    private int examplesCount ;

    /**
     * Adds Dependency injection to share Context information.
     * @param sharedContext
     */
    public AccountHooks(final Context sharedContext) {
        this.context = sharedContext;
    }

    /**
     * Hook that delete an Account saved in Context via API.
     */
    @After(value = "@deleteAccount", order = 1)
    public void deleteAccount() {
        Response response = RequestManager.get("/Account/" + context.getAccount().getId());
        if (response.statusCode() == HttpStatus.SC_OK) {
            RequestManager.delete("/Account/" + context.getAccount().getId());
        }
        WebDriverManager.getInstance().quit();
    }

    @Before(value = "@createAccounts")
    public void createAccounts() throws IOException {
        try {
            RequestManager.get("/Account/");
        } catch(IllegalArgumentException e) {
            String sourceFile = "src/test/resources/files/csv/preconditionAccounts.csv";
            RequestManager.setRequestSpec(ApiAuthenticator.getLoggedReqSpec(
                    context.getUserByAlias("Account Owner User")));
            List<String[]> accountsList = readCSVFile(sourceFile);
            for (String[] accountInfo : accountsList) {
                String body = "{\n" +
                        "\"Name\": \"" + accountInfo[0] + "\", \n" +
                        "\"Site\": \"" + accountInfo[1] + "\", \n" +
                        "\"Phone\": \"" + accountInfo[2] + "\" \n" +
                        "}";
                RequestManager.post("/Account/", body);
                preconditionAccountsList.add(accountInfo[0]);
            }
            examplesCount = 3;
        }
    }

    @After(value = "@deleteAccounts")
    public void deleteAccounts() {
        System.out.println(examplesCount);
        if (examplesCount > 1) {
            examplesCount--;
        } else {
            Response response = RequestManager.get("/Account/");
            List<String> accountNames = response.jsonPath().getList("recentItems.Name");
            List<String> accountIds = response.jsonPath().getList("recentItems.Id");
            for (String accountName : accountNames) {
                if(preconditionAccountsList.contains(accountName)) {
                    int index = accountNames.indexOf(accountName);
                    System.out.println("TO-DELETE " + accountIds.get(index));
                    RequestManager.delete("/Account/" + accountIds.get(index));
                    accountNames.remove(index);
                    accountIds.remove(index);
                }
            }
        }
    }

    public static List<String[]> readCSVFile(final String filePath) throws IOException {
        int count = 0;
        List<String[]> content = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (count != 0) {
                    content.add(line.split(","));
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }
}
