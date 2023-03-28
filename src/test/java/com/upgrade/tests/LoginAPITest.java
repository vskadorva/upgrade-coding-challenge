package com.upgrade.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class LoginAPITest extends AbstractTest {

    private String email = "coding.challenge.login@upgrade.com";
    private String password = "On$3XcgsW#9q";
    private String endpoint = "https://credapi.credify.tech/api/brportorch/v2/login";
    /*
    TODO: create a body payload factory
     */
    private String body = "{ \"username\": \"coding.challenge.login@upgrade.com\", \"password\": \"On$3XcgsW#9q\" }";
    private String bodyNegative = "{ \"username\": \"coding.challenges.login@upgrade.com\", \"password\": \"On$3XcgsW#9q\" }";


    private int statusCode;
    private String productType = "PERSONAL_LOAN";
    private String firstName = "Ian";

    @Test
    public void apiLoginTest() {
        System.out.println("Running validation of apiLoginTest()");
        apiCaller()
                .addHeader("x-cf-corr-id", UUID.randomUUID().toString())
                .addHeader("x-cf-source-id", "coding-challenge")
                .setContentType(ContentType.JSON)
                .setRequestURL(endpoint)
                .postRequest(body)
                .getResponse();

        statusCode = apiCaller().getResponse().getStatusCode();
        Assert.assertEquals(statusCode, 200);
        JsonPath jsonPath = apiCaller().getResponse().jsonPath();
        String firstNameJson = jsonPath.getString("firstName");
        String productTypeJson = jsonPath.getString("loansInReview[0].productType");
        Assert.assertTrue(firstNameJson.equals(firstName));
        Assert.assertTrue(productTypeJson.equals(productType));
    }

    @Test
    public void apiLoginTestNegative() {
        System.out.println("Running validation of apiLoginTestNegative()");
        apiCaller()
                .addHeader("x-cf-corr-id", UUID.randomUUID().toString())
                .addHeader("x-cf-source-id", "coding-challenge")
                .setContentType(ContentType.JSON)
                .setRequestURL(endpoint)
                .postRequest(bodyNegative)
                .getResponse();

        statusCode = apiCaller().getResponse().getStatusCode();
        Assert.assertEquals(statusCode, 401);
    }
}
