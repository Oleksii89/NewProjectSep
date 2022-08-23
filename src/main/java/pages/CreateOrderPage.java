package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreateOrderPage {
    @FindBy(how = How.ID, using = "name")
    private SelenideElement nameField;

    @FindBy(how = How.ID, using = "phone")
    private SelenideElement phoneField;
    @FindBy(how = How.ID, using = "comment")
    private SelenideElement commentField;

    @FindBy(how = How.XPATH, using = "//*[@data-name = 'createOrder-button']")
    private SelenideElement createOrderButton;





    public void inputNameField(String name) {
    nameField.setValue(name);
    }
    public void inputPhoneField(String phone) {
        phoneField.setValue(phone);
    }
    public void inputCommentField(String comment) {
        commentField.setValue(comment);
    }
    public OrderSuccessfullyCreatedPage clickCreateOrderButton() {
        createOrderButton.click();
        return Selenide.page(OrderSuccessfullyCreatedPage.class);
            }
}
