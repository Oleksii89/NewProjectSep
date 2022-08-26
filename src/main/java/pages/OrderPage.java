package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class OrderPage extends BasePage {


    @FindBy(how = How.XPATH, using = "//*[@data-name = 'order-item-0']/span")
    private SelenideElement nameField;
    @FindBy(how = How.XPATH, using = "//*[@data-name = 'order-item-1']/span")
    private SelenideElement phoneField;
    @FindBy(how = How.XPATH, using = "//*[@data-name = 'order-item-2']/span")
    private SelenideElement commentField;




    public String getName() {
        return nameField.getText();
            }
    public String getPhone() {
        return phoneField.getText();
    }
    public String getComment() {
        return commentField.getText();
    }


}
