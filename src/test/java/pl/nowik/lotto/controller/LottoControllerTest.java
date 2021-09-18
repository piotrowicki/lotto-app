package pl.nowik.lotto.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class LottoControllerTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/lotto/stats")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}