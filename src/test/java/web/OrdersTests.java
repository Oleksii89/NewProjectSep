package web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CreateOrderPage;
import pages.LoginPage;
import pages.OrderPage;

import static com.codeborne.selenide.Selenide.open;

public class OrdersTests {
    @Test
    public void createOrderTest() {
        LoginPage loginPage = open("http://51.250.6.164:3000/signin", LoginPage.class);
        loginPage.inputLogin("user4");
        loginPage.inputPassword("hellouser4");
        CreateOrderPage createOrderPage = loginPage.clickSignInButton();
        createOrderPage.inputNameField("Oleksii");
        createOrderPage.inputPhoneField("1235431243");
        createOrderPage.inputCommentField("create order");
        createOrderPage.clickCreateOrderButton();
       // $(By.xpath("//*[@data-name = 'orderSuccessfullyCreated-popup']")).shouldBe(Condition.enabled);
        String orderId = createOrderPage.getOrderId();
        createOrderPage.clickOrderSuccessfullyCreatedButton();
        createOrderPage.clickStatusButton();
        createOrderPage.inputSearchOrderField(orderId);
        OrderPage orderPage = createOrderPage.clickSearchOrderSubmitButton();

        Assertions.assertEquals("Oleksii", orderPage.getName(), "Name field in order is not equal");
        Assertions.assertEquals("1235431243", orderPage.getPhone(), "Phone field in order is not equal");
        Assertions.assertEquals("create order", orderPage.getComment(), "Comment field in order is not equal");


    }
}
