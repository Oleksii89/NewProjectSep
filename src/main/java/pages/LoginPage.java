package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    @FindBy(how = How.ID, using = "username")
    private SelenideElement loginField;

    @FindBy(how = How.ID, using = "password")
    private SelenideElement passwordField;
    @FindBy(how = How.XPATH, using = "//*[@data-name = 'signIn-button']")
    private SelenideElement signInButton;


    public void inputLogin(String username) {
        loginField.setValue(username);
    }

    public void inputPassword(String password) {
        passwordField.setValue(password);
    }
    public CreateOrderPage clickSignInButton() {
        signInButton.click();
        return Selenide.page(CreateOrderPage.class);
    }
}
