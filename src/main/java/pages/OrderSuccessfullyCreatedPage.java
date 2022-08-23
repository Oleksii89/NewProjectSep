package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class OrderSuccessfullyCreatedPage {
    @FindBy(how = How.XPATH, using = "//*[data-name = 'orderSuccessfullyCreated-popup']")
    private SelenideElement orderSuccessfullyCreatedPopUp;
}
