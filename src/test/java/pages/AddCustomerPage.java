package pages;

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

    public void inputPostCode() {
        waitUntilVisible(webDriver, postCodeField);
        postCode = dataGenerator.postCodeGenerator();
        postCodeField.sendKeys(postCode);
        new AddCustomerPage(webDriver);
    }

    public void inputFirstName(){
        waitUntilVisible(webDriver, firstNameField);
        log.info("Post code: {}", postCode);
        firstNameField.sendKeys(dataGenerator.firstNameGenerator(postCode));
        new AddCustomerPage(webDriver);
    }

    public void inputLastName(String lastName){
        waitUntilVisible(webDriver, lastNameField);
        lastNameField.sendKeys(lastName);
        new AddCustomerPage(webDriver);
    }

    public void clickButton() {
        waitToClick(webDriver, addCustomerButton);
        addCustomerButton.click();
        new AddCustomerPage(webDriver);
    }

    public String getPostCodeField(){
        waitUntilVisible(webDriver, postCodeField);
        return postCodeField.getAttribute("value");
    }

    public String getLastName(){
        waitUntilVisible(webDriver, lastNameField);
        return lastNameField.getAttribute("value");
    }
}
