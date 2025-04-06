package tests.apitests;

import helpers.BaseRequests;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.Entity;

import static io.restassured.RestAssured.given;

public class DeleteEntityTest {

    public RequestSpecification requestSpecification;
    private String entityId;

    @BeforeClass
    void init(){
        Entity entity = Entity.builder().build();

        entityId = given()
                .spec(requestSpecification)
                .body(entity)
                .when()
                .post("/create")
                .then()
                .statusCode(200)
                .extract().asString();
    }

    @Test
    void deleteEntityTest() {
        BaseRequests.deleteEntityById(entityId);
    }
}
