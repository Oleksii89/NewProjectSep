package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreateOrderPage extends BasePage {
    @FindBy(how = How.ID, using = "name")
    private SelenideElement nameField;

    @FindBy(how = How.ID, using = "phone")
    private SelenideElement phoneField;
    @FindBy(how = How.ID, using = "comment")
    private SelenideElement commentField;

    @FindBy(how = How.XPATH, using = "//*[@data-name = 'createOrder-button']")
    private SelenideElement createOrderButton;

    @FindBy(how = How.XPATH, using = "//*[@data-name = 'orderSuccessfullyCreated-popup']/span")
    private SelenideElement orderId;

    @FindBy(how = How.XPATH, using = "//*[@data-name = 'orderSuccessfullyCreated-popup-ok-button']")
    private SelenideElement orderSuccessfullyCreatedButton;

    @FindBy(how = How.XPATH, using = "//*[@data-name = 'openStatusPopup-button']")
    private SelenideElement statusButton;
    @FindBy(how = How.NAME, using = "id")
    private SelenideElement searchOrderInput;

    @FindBy(how = How.XPATH, using = "//*[@data-name = 'searchOrder-submitButton']")
    private SelenideElement searchOrderSubmitButton;




    public void inputNameField(String name) {
    nameField.setValue(name);
    }
    public void inputPhoneField(String phone) {
        phoneField.setValue(phone);
    }
    public void inputCommentField(String comment) {
        commentField.setValue(comment);
    }
    public void clickCreateOrderButton() {
        createOrderButton.click();
     }
    public void clickStatusButton() {
        statusButton.click();
      }
    public void inputSearchOrderField(String orderId) {

        searchOrderInput.setValue(orderId);
    }
     public void clickOrderSuccessfullyCreatedButton() {
        orderSuccessfullyCreatedButton.click();
     }

     public OrderPage clickSearchOrderSubmitButton() {
        searchOrderSubmitButton.click();
        return Selenide.page(OrderPage.class);
     }

    public String getOrderId() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return orderId.getText().replaceAll("\\D+","");
    }
}
