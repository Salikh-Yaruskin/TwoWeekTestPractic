package tests;

import helpers.PropertyProvider;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BasicTest {

    protected WebDriver webDriver;

    @BeforeClass
    @Step("Открытие страницы")
    void init(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        webDriver = new ChromeDriver(chromeOptions);
    }

    @AfterClass
    @Step("Закрытие WebDriver")
    void end(){
        webDriver.quit();
    }
}
