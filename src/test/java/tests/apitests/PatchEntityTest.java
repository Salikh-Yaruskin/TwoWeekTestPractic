package tests.apitests;

import helpers.BaseRequests;
import helpers.PropertyProvider;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.Entity;
import pojo.ResponseEntity;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Slf4j
public class PatchEntityTest {

    private RequestSpecification requestSpecification;
    private final int entityId = Integer.parseInt(PropertyProvider.getInstance().getProperty("property.api_test.entity_id"));
    private ResponseEntity responseEntity;

    @BeforeClass
    void init() {
        responseEntity = given().when()
                .get("http://localhost:8080/api/get/" + entityId)
                .then()
                .statusCode(200)
                .extract().as(ResponseEntity.class, ObjectMapperType.JACKSON_2);
    }

    @Test
    void patchEntityTest() {
        requestSpecification = BaseRequests.initRequestSpecification();

        Entity entity = Entity.builder()
                .title("It`s new title")
                .build();

        given()
                .spec(requestSpecification)
                .body(entity)
                .when()
                .patch("/patch/" + entityId)
                .then()
                .statusCode(204);

        assertNotEquals(entity.getTitle(), responseEntity.getTitle());
    }

    @AfterClass
    void end() {
        given()
                .spec(requestSpecification)
                .body(responseEntity)
                .when()
                .patch("/patch/" + entityId)
                .then()
                .statusCode(204);
    }
}
