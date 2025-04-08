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
        responseEntity = BaseRequests.getEntityById(entityId);
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

        ResponseEntity afterPatchEntity = BaseRequests.getEntityById(entityId);

        assertNotEquals(afterPatchEntity.getTitle(), responseEntity.getTitle());

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
