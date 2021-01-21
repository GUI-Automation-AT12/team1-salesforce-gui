package org.fundacionjala.salesforce.utils;

import io.restassured.response.Response;
import org.fundacionjala.salesforce.constants.AccountConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class ApiResponseDataExtractor {

    private ApiResponseDataExtractor() {
    }

    private static HashMap<String, String> composeStrategyAccountMap(final Response response) {
        HashMap<String, String> strategyMap = new HashMap<>();
        strategyMap.put(AccountConstants.NAME_KEY, response.jsonPath().getString("Name"));
        strategyMap.put(AccountConstants.PARENT_ACCOUNT_KEY, response.jsonPath().getString("ParentId"));
        strategyMap.put(AccountConstants.SITE_KEY, response.jsonPath().getString("Site"));
        strategyMap.put(AccountConstants.PHONE_KEY, response.jsonPath().getString("Phone"));
        strategyMap.put(AccountConstants.BILLING_CITY_KEY, response.jsonPath().getString("BillingCity"));
        strategyMap.put(AccountConstants.RATING_KEY, response.jsonPath().getString("Rating"));
        strategyMap.put(AccountConstants.DESCRIPTION_KEY, response.jsonPath().getString("Description"));
        return strategyMap;
    }

    /**
     * Extracts Account data for selected fields from API response.
     * @param response to extract data
     * @param fields selected to get data
     * @return account Info as Map
     */
    public static Map<String, String> getAccountDataFromApi(final Response response, final Set<String> fields) {
        Map<String, String> accountInfoMap = new HashMap<>();
        fields.forEach(field -> accountInfoMap.put(field, composeStrategyAccountMap(response).get(field)));
        return accountInfoMap;
    }
}
