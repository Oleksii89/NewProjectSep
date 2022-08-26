package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BasePage {
    @FindBy (how = How.XPATH, using = "//*[@data-name = 'mainPage-link']")
    private SelenideElement companyLogo;

    public CreateOrderPage clickCompanyLogo() {
        companyLogo.click();
        return Selenide.page(CreateOrderPage.class);
    }
}
