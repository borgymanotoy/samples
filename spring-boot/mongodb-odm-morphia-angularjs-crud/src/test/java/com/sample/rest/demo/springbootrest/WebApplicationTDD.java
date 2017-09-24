package com.sample.rest.demo.springbootrest;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.get;

public class WebApplicationTDD {

    @Test
    public void testListUsers() {
        get("/api/listUsers").then().assertThat().statusCode(200);
        get("/api/getUser?username=borgymanotoy").then()
                .assertThat().statusCode(200);
    }

}