package com.upgrade.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class ApiCaller {

    private Response response;
    private RequestSpecification requestSpecification = RestAssured.given();
    private ContentType contentType;
    private Map<String, String> headers = new HashMap<>();
    private String endpoint;

    public ApiCaller postRequest(Object body) {
        System.out.println("Generating POST request: " + endpoint);

        response = this.requestSpecification
                .contentType(contentType)
                .headers(headers)
                .body(body)
                .log()
                .body(true)
                .log()
                .headers()
                .when()
                .post(endpoint);

        try {
            response
                    .then();
        } catch (AssertionError error) {
            throw new RuntimeException("", error);
        }
        response
                .then()
                .log()
                .body(true);


        return this;
    }

    public ApiCaller setRequestURL(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public ApiCaller setContentType(ContentType contentType) {
        this.contentType = contentType;
        return this;
    }

    public Response getResponse() {
        return response;
    }

    public ApiCaller addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }
}
