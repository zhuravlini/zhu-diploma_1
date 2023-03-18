package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class API {

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static String buyForm(DataHelper.CardData cardData) {
        return given()
                .spec(requestSpecification)
                .body(cardData)
                .when()
                .post("/api/v1/pay")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response().asString();
    }

    public static String creditForm(DataHelper.CardData cardData) {
        return given()
                .spec(requestSpecification)
                .body(cardData)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200)
                .extract().response().asString();
    }


}