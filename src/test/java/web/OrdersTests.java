package web;

import api.CourierFunctions;
import api.LoginFunctions;
import api.OrderFunctions;
import dto.Courier;
import dto.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CreateOrderPage;
import pages.LoginPage;
import pages.OrderPage;

import java.util.HashMap;
import java.util.Map;

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

        Order requestOrder = new Order();

        requestOrder.setStatus("OPEN");
        requestOrder.setCustomerName("Samuel Test2");
        requestOrder.setCourierId(123L);
        requestOrder.setCustomerPhone("111122223333");
        requestOrder.setComment("Hey guys2");

        OrderFunctions testOrderFunctions = new OrderFunctions();

        Order responseOrder = testOrderFunctions.postNewOrder(requestOrder, headers);

        // после логина студентом и создания заказа отправить запрос на создаение курьера

        Courier requestCourier = new Courier();

        requestCourier.setLogin("newcourier1");
        requestCourier.setPassword("newcourier1");
        requestCourier.setName("newcourier1");

        CourierFunctions courierFunctions = new CourierFunctions();

        Courier responseCourier = courierFunctions.postNewCourier(requestCourier,headers);

        Assertions.assertEquals(responseCourier.getLogin(), "newcourier1", "error message");







//        //UI
//        LoginPage loginPage = open("/signin", LoginPage.class);
//        loginPage.inputLogin("user4");
//        loginPage.inputPassword("hellouser4");
//        CreateOrderPage createOrderPage = loginPage.clickSignInButton();
//        createOrderPage.inputNameField("Oleksii");
//        createOrderPage.inputPhoneField("1235431243");
//        createOrderPage.inputCommentField("create order");
//        createOrderPage.clickCreateOrderButton();
//        createOrderPage.clickOrderSuccessfullyCreatedButton();
//        createOrderPage.clickStatusButton();
//        createOrderPage.inputSearchOrderField(responseOrder.getId().toString());
//        OrderPage orderPage = createOrderPage.clickSearchOrderSubmitButton();
//
//        // проверка статусов заказа UI
//        $(By.xpath("//*[@data-name = 'status-item-0']/div/span")).shouldHave(attribute("class","status-list__status status-list__status_active"));
//        //  Selenide.refresh(); // обновление страницы UI
//        $(By.xpath("//*[@data-name = 'status-item-1']/div/span")).shouldHave(attribute("class","status-list__status status-list__status_active"));
//        //  Selenide.refresh(); // обновление страницы UI
//        $(By.xpath("//*[@data-name = 'status-item-2']/div/span")).shouldHave(attribute("class","status-list__status status-list__status_active"));
//        //  Selenide.refresh(); // обновление страницы UI
//        $(By.xpath("//*[@data-name = 'status-item-3']/div/span")).shouldHave(attribute("class","status-list__status status-list__status_active"));

    }
}
