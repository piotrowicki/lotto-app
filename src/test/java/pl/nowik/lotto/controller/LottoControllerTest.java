package pl.nowik.lotto.controller;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import pl.nowik.lotto.service.LottoStatisticsService;

@QuarkusTest
@TestHTTPEndpoint(LottoController.class)
public class LottoControllerTest {

    @InjectMock
    LottoStatisticsService service;

    @Test
    public void testHelloEndpoint() {
        given()
                .when()
                .get("/lotto/stats")
                .then()
                .statusCode(200);
    }
}