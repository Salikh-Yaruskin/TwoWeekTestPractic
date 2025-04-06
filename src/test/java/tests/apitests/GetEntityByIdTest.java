package tests.apitests;

import helpers.PropertyProvider;
import io.restassured.mapper.ObjectMapperType;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.Entity;
import pojo.ResponseEntity;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@Slf4j
public class GetEntityByIdTest {

    private final int entityId = Integer.parseInt(PropertyProvider.getInstance().getProperty("property.api_test.entity_id"));

    @Test
    void getEntityByIdTest() {
        int arraySize = Integer.parseInt(PropertyProvider.getInstance().getProperty("property.api_test.important_numbers_size"));

        ResponseEntity entity = given().when()
                .get("http://localhost:8080/api/get/" + entityId)
                .then()
                .statusCode(200)
                .extract().as(ResponseEntity.class, ObjectMapperType.JACKSON_2);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(entity.getTitle(), PropertyProvider.getInstance().getProperty("property.api_test.title"));
        softAssert.assertEquals(entity.getImportantNumbers().size(), arraySize);
    }
}
