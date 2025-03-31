package tests;

import helpers.PropertyProvider;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CustomersPage;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Epic("Управление пользователями")
@Feature("Удаление пользователя")
public class DeleteCustomerTest {

    private WebDriver webDriver;

    @BeforeClass
    @Step("Открытие страницы клиентов")
    void init(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.customer"));
    }

    @Test
    @Story("Удаление пользователя со средним именем")
    @Description("Тест проверяет, что удаляется клиент с именем, равным среднему по длине.")
    void deleteAvgCustomer() {
        CustomersPage customersPage = new CustomersPage(webDriver);
        List<String> namesBefore = customersPage.getCustomerNames();

        assertEquals(customersPage.getAvgName(), PropertyProvider.getInstance().getProperty("property.firstAvgName"));

        customersPage.deleteAvgCustomer();
        List<String> namesAfter = customersPage.getCustomerNames();

        assertNotEquals(namesBefore, namesAfter);
    }

    @AfterClass
    @Step("Закрытие WebDriver")
    void end(){
        webDriver.quit();
    }
}
