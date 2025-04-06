package tests.apitests;

import com.fasterxml.jackson.core.JsonProcessingException;
import helpers.BaseRequests;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.Entity;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

@Slf4j
public class CreateEntityTest {

    public RequestSpecification requestSpecification;
    private String entityId;

    @BeforeClass
    void init(){
        requestSpecification = BaseRequests.initRequestSpecification();
    }

    @Test
    void createEntityTest() throws JsonProcessingException {
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

    @AfterClass
    void deleteEntityTest() {
        BaseRequests.deleteEntityById(entityId);
    }
}
