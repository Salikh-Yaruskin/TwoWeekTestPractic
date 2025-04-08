package helpers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.ResponseEntity;

import static io.restassured.RestAssured.given;


public class BaseRequests {

    public static RequestSpecification initRequestSpecification(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .setBaseUri(PropertyProvider.getInstance().getProperty("property.api_test.url"))
                .setAccept(ContentType.JSON);
        return requestSpecBuilder.build();
    }

    public static void deleteEntityById(String id) {
        given()
                .when()
                .delete("/api/delete/" + id)
                .then()
                .statusCode(204);
    }

    public static ResponseEntity getEntityById(int entityId) {
        return given().when()
                .get(PropertyProvider.getInstance().getProperty("property.api_test.url") +"/get/" + entityId)
                .then()
                .statusCode(200)
                .extract().as(ResponseEntity.class, ObjectMapperType.JACKSON_2);
    }
}
