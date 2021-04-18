package com.ammerzon

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test

@QuarkusTest
class RatingsResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/ratings")
          .then()
             .statusCode(200)
    }

}