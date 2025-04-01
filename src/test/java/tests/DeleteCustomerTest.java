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
import pages.CustomersPage;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Slf4j
@Epic("Управление пользователями")
@Feature("Удаление пользователя")
public class DeleteCustomerTest extends BasicTest{

    @Test
    @Story("Удаление пользователя со средним именем")
    @Description("Тест проверяет, что удаляется клиент с именем, равным среднему по длине.")
    void deleteAvgCustomer() {
        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.customer"));
        log.info("Url " + PropertyProvider.getInstance().getProperty("web.url.customer"));
        CustomersPage customersPage = new CustomersPage(webDriver);
        List<String> namesBefore = customersPage.getCustomerNames();

        assertEquals(customersPage.getAvgName(), PropertyProvider.getInstance().getProperty("property.firstAvgName"));

        customersPage.deleteAvgCustomer();
        List<String> namesAfter = customersPage.getCustomerNames();

        assertNotEquals(namesBefore, namesAfter);
    }
}
