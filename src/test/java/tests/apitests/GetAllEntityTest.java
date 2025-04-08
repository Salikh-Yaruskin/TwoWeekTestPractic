package tests.apitests;

import helpers.PropertyProvider;
import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapperType;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pojo.ResponseEntity;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

@Slf4j
public class GetAllEntityTest {

    @Test
    void getEntityTest() {
        int allEntitySize = Integer.parseInt(PropertyProvider.getInstance().getProperty("property.api_test.all_entity_size"));

        List<ResponseEntity> entityList = given()
                .when()
                .get(PropertyProvider.getInstance().getProperty("property.api_test.url") + "/getAll")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("entity", ResponseEntity.class);

        assertEquals(entityList.size(), allEntitySize);
    }
}
