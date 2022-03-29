package pl.nowik.lotto.controller;

import static io.restassured.RestAssured.given;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class OpenApiTest {

    @Test
    public void testOpenApi() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .get("/q/openapi")
                .then()
                .statusCode(200);
    }

    @Test
    public void testSwaggerUi() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .get("/q/swagger-ui")
                .then()
                .statusCode(200);
    }
}