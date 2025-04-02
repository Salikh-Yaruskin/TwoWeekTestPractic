package tests;

import helpers.PropertyProvider;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddCustomerPage;

import static org.testng.Assert.assertEquals;

@Slf4j
@Epic("Управление пользователями")
@Feature("Добавление нового пользователя")
public class AddCustomerTest extends BasicTest{


    @Test
    @Story("Создание нового пользователя")
    @Description("Тест проверяет возможность добавления нового пользователя.")
    void addCustomerTest() {
        SoftAssert softAssert = new SoftAssert();

        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.addCustomer"));
        AddCustomerPage addCustomerPage = new AddCustomerPage(webDriver);
        addCustomerPage.inputPostCode()
                .inputFirstName()
                .inputLastName(PropertyProvider.getInstance().getProperty("property.lastname"));

        softAssert.assertEquals(addCustomerPage.getPostCodeField(), addCustomerPage.getPostCode());
        softAssert.assertEquals(addCustomerPage.getLastName(), "last_name_user");

        addCustomerPage.clickButton();
    }
}
