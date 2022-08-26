package api;

import dto.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class NewTests {
    private static String token = null;
    private static Map<String, String> headers = new HashMap<>();


    @BeforeAll
    static void setup() {
        LoginFunctions loginFunctions = new LoginFunctions();
        token = loginFunctions.loginAsStudent("user1", "user4");

        headers.put("Authorization", token);
        headers.put("Content-type", "application/json");

    }


    @Test
    public void postOrder(){
        Order requestOrder = new Order();

        requestOrder.setStatus("OPEN");
        requestOrder.setCustomerName("Samuel");
        requestOrder.setCourierId(432L);
        requestOrder.setCustomerPhone("5564879");
        requestOrder.setComment("Hey guys");

        OrderFunctions testOrderFunctions = new OrderFunctions();

        Order responseOrder = testOrderFunctions.postNewOrder(requestOrder, headers);

        System.out.println(requestOrder.toString());

//        Gson gson = new Gson();
//        String stringRequestOrder = gson.toJson(requestOrder);

//        given().
//        header ("Content-type", "application/json").
//        body(stringRequestOrder).
//        when().
//        post(urlSwagger+"/test-orders").
//        then().
//        statusCode(200).
//        body("status", equalTo("OPEN"),
//                "customerName", equalTo("Samuel"),
//                "courierId", equalTo(432),
//                "customerPhone", equalTo("5564879"),
//                "comment", equalTo("Hey guys"));

//        Response response = given().
//                header("Content-type", "application/json").
//                body(stringRequestOrder).
//                when().
//                post(urlSwagger+"/test-orders").
//                then().
//                statusCode(200).extract().response();
//
//        Order responseOrder = gson.fromJson(response.body().asString(), Order.class);
//
//        Assertions.assertEquals(responseOrder.getStatus(), "OPEN", "status error");
//        Assertions.assertEquals(responseOrder.getCustomerName(), "Samuel", "incorrect name");
//        Assertions.assertEquals(responseOrder.getCourierId(),432, "incorrect courier ID");
//        Assertions.assertEquals(responseOrder.getCustomerPhone(),"5564879", "incorrect customer phone");
//        Assertions.assertEquals(responseOrder.getComment(), "Hey guys", "incorrect comment");
//
//        System.out.println(responseOrder.toString());

//
//
//
//
//    Response responseOrders = given().
//            headers(headers).
//            when().
//            get("http://51.250.6.164:8080/orders").
//            then().
//            statusCode(200).extract().response();
//
//        System.out.println(responseOrders.body().asString());


    }
    //    @Test
//    public void catVoiceTest() {
//        Cat Zombik = new Cat();
//
//        Assertions.assertEquals("Meow-Meow", Zombik.voiceCat, "Cat doesn't say anything");
//    }
//
//    @Test
//    public void dogVoiceTest() {
//        Dog Betty = new Dog();
//
//        Assertions.assertEquals("Gav-Gav", Betty.voiceDog, "Dog is silent");
//    }

}
