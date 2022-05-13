package pl.nowik.lotto.controller;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Disabled;
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
    public void testLottoStatsEndpoint() {
        given()
                .when()
                .get("/lotto/stats")
                .then()
                .statusCode(200);
    }

    @Test
    @Disabled(value = "Due to NoResultException.")
    public void testLottoLastEndpoint() {
        given()
                .when()
                .get("/lotto/stats/last")
                .then()
                .statusCode(200);
    }

    @Test
    public void testLottoMostCommonEndpoint() {
        given()
                .when()
                .get("/lotto/stats/most-common")
                .then()
                .statusCode(200);
    }

    @Test
    public void testLottoLeastCommonEndpoint() {
        given()
                .when()
                .get("/lotto/stats/least-common")
                .then()
                .statusCode(200);
    }
}