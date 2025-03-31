package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static helpers.WaitHelper.waitUntilVisible;

public class CustomersPage extends BasePage {

    @FindBy(xpath = "//table//tr//td//a[contains(@ng-click, 'fName')]")
    WebElement customerNameFilter;

    @FindBy(xpath = "//tbody//tr//td[1]")
    List<WebElement> listCustomerName;

    @FindBy(xpath = "//tbody//tr//td//button")
    List<WebElement> listDeleteButton;

    public CustomersPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickFilter() {
        waitUntilVisible(webDriver, customerNameFilter);
        customerNameFilter.click();
        new CustomersPage(webDriver);
    }

    public List<String> getCustomerNames() {
        waitUntilVisible(webDriver, customerNameFilter);
        List<String> names = listCustomerName.stream()
                .map(WebElement::getText)
                .toList();

        return names;
    }

    public String getAvgName() {
        waitUntilVisible(webDriver, customerNameFilter);
        Double avg = getCustomerNames().stream()
                .collect(Collectors.averagingDouble(String::length));

        return getCustomerNames().stream()
                .filter(name -> name.length() == avg.intValue())
                .findFirst()
                .orElse(null);
    }

    public void deleteAvgCustomer() {
        int index = IntStream.range(0, listCustomerName.size())
                .filter(x -> listCustomerName.get(x).getText().equals(getAvgName()))
                .findFirst()
                .orElse(-1);
        if (index != -1)
            listDeleteButton.get(index).click();
    }
}
