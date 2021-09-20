package pl.nowik.lotto.controller;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class LottoControllerTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/lotto/stats")
          .then()
             .statusCode(200);
    }
}