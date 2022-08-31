package web;

import api.CourierFunctions;
import api.LoginFunctions;
import api.OrderFunctions;
import com.codeborne.selenide.Selenide;
import dto.Courier;
import dto.Order;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.CreateOrderPage;
import pages.LoginPage;
import pages.OrderPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrdersTests extends BaseWebTests {
    @Test
    public void createOrderTest() {
        LoginPage loginPage = open("/signin", LoginPage.class);
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

    @Test
    public void createOrderIntegrationTest() {

        //API
        LoginFunctions loginFunctions = new LoginFunctions();
        String token = loginFunctions.loginAsStudent("user4","hellouser4");

        Map<String, String> headers = new HashMap<>();

        headers.put ("Authorization", token);
        headers.put ("Content-type", "application/json");

        Order requestOrder = new Order();

        requestOrder.setStatus("OPEN");
        requestOrder.setCustomerName("Samuel Test1");
        requestOrder.setCourierId(123L);
        requestOrder.setCustomerPhone("111122223333");
        requestOrder.setComment("Hey guys1");

        OrderFunctions testOrderFunctions = new OrderFunctions();

        Order responseOrder = testOrderFunctions.postNewOrder(requestOrder, headers);

        //UI
        LoginPage loginPage = open("/signin", LoginPage.class);
        loginPage.inputLogin("user4");
        loginPage.inputPassword("hellouser4");
        CreateOrderPage createOrderPage = loginPage.clickSignInButton();
        createOrderPage.inputNameField("Oleksii");
        createOrderPage.inputPhoneField("1235431243");
        createOrderPage.inputCommentField("create order");
        createOrderPage.clickCreateOrderButton();
        createOrderPage.clickOrderSuccessfullyCreatedButton();
        createOrderPage.clickStatusButton();
        createOrderPage.inputSearchOrderField(responseOrder.getId().toString());
        OrderPage orderPage = createOrderPage.clickSearchOrderSubmitButton();

        Assertions.assertEquals("Samuel Test1", orderPage.getName(), "Name field in order is not equal");
        Assertions.assertEquals("111122223333", orderPage.getPhone(), "Phone field in order is not equal");
        Assertions.assertEquals("Hey guys1", orderPage.getComment(), "Comment field in order is not equal");


    }

    @Test
    public void createOrderAndChangeStatusIntegrationTest (){
        //API
        LoginFunctions loginFunctions = new LoginFunctions();
        String token = loginFunctions.loginAsStudent("user4","hellouser4"); // login as student

        Map<String, String> headers = new HashMap<>();

        headers.put ("Authorization", token);
        headers.put ("Content-type", "application/json");


        // после логина студентом создаем курьера

        Courier requestCourier = new Courier();
        requestCourier.setLogin(RandomStringUtils.randomAlphabetic(6));
        requestCourier.setName("newcourier4");
        requestCourier.setPassword("newcourier4");

        CourierFunctions courierFunctions = new CourierFunctions();
        Courier responseCourier = courierFunctions.postNewCourier(requestCourier,headers);

        // логин Курьером
        token = loginFunctions.loginAsCourier(responseCourier.getLogin(), requestCourier.getPassword());

        headers.replace("Authorization", token);


          //UI
        LoginPage loginPage = open("/signin", LoginPage.class);
        loginPage.inputLogin("user4");
        loginPage.inputPassword("hellouser4");
        CreateOrderPage createOrderPage = loginPage.clickSignInButton();
        createOrderPage.inputNameField("Oleksii");
        createOrderPage.inputPhoneField("1235431243");
        createOrderPage.inputCommentField("create order");
        createOrderPage.clickCreateOrderButton();
        String orderId = createOrderPage.getOrderId();
        createOrderPage.clickOrderSuccessfullyCreatedButton();
        createOrderPage.clickStatusButton();
        createOrderPage.inputSearchOrderField(orderId);
        OrderPage orderPage = createOrderPage.clickSearchOrderSubmitButton();

        //API
        OrderFunctions orderFunctions = new OrderFunctions();
        orderFunctions.putOrderToAssign(Integer.valueOf(orderId), headers);

        // проверка статусов заказа UI
        Selenide.refresh(); // обновление страницы UI
        $(By.xpath("//*[@data-name = 'status-item-1']/div/span")).shouldHave(attribute("class","status-list__status status-list__status_active"));

        orderFunctions.putOrderToInprogress(Integer.valueOf(orderId), headers); // меняем статус заказа

        Selenide.refresh(); // обновление страницы UI
        $(By.xpath("//*[@data-name = 'status-item-2']/div/span")).shouldHave(attribute("class","status-list__status status-list__status_active"));

        orderFunctions.putOrderToDelivered(Integer.valueOf(orderId), headers); // меняем статус заказа

        Selenide.refresh(); // обновление страницы UI
        $(By.xpath("//*[@data-name = 'status-item-3']/div/span")).shouldHave(attribute("class","status-list__status status-list__status_active"));

    }


}
