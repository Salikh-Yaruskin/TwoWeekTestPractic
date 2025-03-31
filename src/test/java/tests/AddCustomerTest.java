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
import pages.AddCustomerPage;

import static org.testng.Assert.assertEquals;

@Slf4j
@Epic("Управление пользователями")
@Feature("Добавление нового пользователя")
public class AddCustomerTest {

    private WebDriver webDriver;

    @BeforeClass
    @Step("Открытие страницы")
    void init(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.addCustomer"));
    }

    @Test
    @Story("Создание нового пользователя")
    @Description("Тест проверяет возможность добавления нового пользователя.")
    void addCustomerTest() {
        AddCustomerPage addCustomerPage = new AddCustomerPage(webDriver);
        addCustomerPage.inputPostCode();
        addCustomerPage.inputFirstName();
        addCustomerPage.inputLastName(PropertyProvider.getInstance().getProperty("property.lastname"));

        assertEquals(addCustomerPage.getPostCodeField(), addCustomerPage.getPostCode());
        assertEquals(addCustomerPage.getLastName(), "last_name_user");

        addCustomerPage.clickButton();
    }

    @AfterClass
    @Step("Закрытие WebDriver")
    void end(){
        webDriver.quit();
    }
}
