package web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import pages.CreateOrderPage;
import pages.LoginPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTests {

//    @Test
//    public void userAuthError() {
//        open("http://51.250.6.164:3000/signin");
//        $(By.id("username")).setValue("hello");
//        $(By.id("password")).setValue("12345678");
//        try {
//            Thread.sleep(3000);
//        }
//        catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        $(By.xpath("//*[data-name = 'signIn-button']")).click();
//        $(By.xpath("//*[data-name = 'authorizationError-popup-close-button']")).shouldBe(Condition.visible);
//
//    }

    @Test
    public void loginAndCheck() {
        LoginPage loginPage = open("http://51.250.6.164:3000/signin", LoginPage.class);
        loginPage.inputLogin("user4");
        loginPage.inputPassword("hellouser4");
        CreateOrderPage createOrderPage = loginPage.clickSignInButton();
        $(By.xpath("//*[@data-name = 'openStatusPopup-button']")).shouldBe(Condition.enabled);
        $(By.xpath("//*[@data-name = 'createOrder-button']")).shouldBe(Condition.enabled);

    }


}
