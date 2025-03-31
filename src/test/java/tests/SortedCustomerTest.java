package tests;

import helpers.PropertyProvider;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CustomersPage;

import java.util.List;

import static org.testng.Assert.assertNotEquals;

@Epic("Управление пользователями")
@Feature("Сортировка клиентов")
public class SortedCustomerTest {

    private WebDriver webDriver;

    @BeforeClass
    @Step("Открытие страницы клиентов")
    void init(){
        webDriver =  new ChromeDriver();
        webDriver.get(PropertyProvider.getInstance().getProperty("web.url.customer"));
    }

    @Test
    @Story("Сортировка клиентов по имени")
    @Description("Этот тест проверяет, что список клиентов корректно сортируется по имени.")
    void sortedByName() throws InterruptedException {
        CustomersPage customersPage = new CustomersPage(webDriver);
        List<String> namesBefore = customersPage.getCustomerNames();
        customersPage.clickFilter();
        List<String> namesAfter = customersPage.getCustomerNames();
        assertNotEquals(namesBefore, namesAfter, "Customers are not sorted by name");
    }

    @AfterClass
    @Step("Закрытие WebDriver")
    void end(){
        webDriver.quit();
    }
}
