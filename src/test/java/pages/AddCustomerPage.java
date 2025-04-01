package pages;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.DataGenerator;

import static helpers.WaitHelper.waitToClick;
import static helpers.WaitHelper.waitUntilVisible;

@Slf4j
public class AddCustomerPage extends BasePage{

    @FindBy(xpath = "//div[@class='form-group']//input[@placeholder='Post Code']")
    WebElement postCodeField;

    @FindBy(xpath = "//div[@class='form-group']//input[@placeholder='First Name']")
    WebElement firstNameField;

    @FindBy(xpath = "//div[@class='form-group']//input[@placeholder='Last Name']")
    WebElement lastNameField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement addCustomerButton;

    @Getter
    private String postCode;
    private final DataGenerator dataGenerator;

    public AddCustomerPage(WebDriver driver) {
        super(driver);
        dataGenerator = new DataGenerator();
    }

    @Step("Заполнение поля Post Code")
    public AddCustomerPage inputPostCode() {
        waitUntilVisible(webDriver, postCodeField);
        postCode = dataGenerator.postCodeGenerator();
        postCodeField.sendKeys(postCode);
        return this;
    }

    @Step("Заполнение поля First Name")
    public AddCustomerPage inputFirstName(){
        waitUntilVisible(webDriver, firstNameField);
        log.info("Post code: {}", postCode);
        firstNameField.sendKeys(dataGenerator.firstNameGenerator(postCode));
        return this;
    }

    @Step("заполнение поля Last Name")
    public AddCustomerPage inputLastName(String lastName){
        waitUntilVisible(webDriver, lastNameField);
        lastNameField.sendKeys(lastName);
        return this;
    }

    @Step("Нажатиен на кнопку Add Customer")
    public AddCustomerPage clickButton() {
        waitToClick(webDriver, addCustomerButton);
        addCustomerButton.click();
        return this;
    }

    @Step("Получение значения поля Post Code")
    public String getPostCodeField(){
        waitUntilVisible(webDriver, postCodeField);
        return postCodeField.getAttribute("value");
    }

    @Step("Получение значения поля Last Name")
    public String getLastName(){
        waitUntilVisible(webDriver, lastNameField);
        return lastNameField.getAttribute("value");
    }
}
