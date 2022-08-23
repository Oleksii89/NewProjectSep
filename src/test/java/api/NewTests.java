package api;

import api.TestOrderFunctions;
import com.google.gson.Gson;
import dto.Order;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.equalTo;


public class NewTests {
    private String urlSwagger = "http://51.250.6.164:8080";
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
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", useHeadersInDisplayName = true)
    void testWithFileSourceAndHeaders(String Login, String Password, String Role) {
        Assertions.assertNotNull(Login);
        Assertions.assertNotEquals("", Password);
        Assertions.assertNotEquals("", Role);
    }

    @BeforeAll
    static void setup() {


    Response response = given().
            header("Content-type", "application/json").
            body("{\n" +
                " \"username\": \"user4\", \n" +
                " \"password\": \"hellouser4\"\n" +
                "}").
            when().
            post("http://51.250.6.164:8080/login/student").
            then().
            statusCode(200).extract().response();

    Map<String, String> headers = new HashMap<>();
    headers.put("Content-type", "application/json");
    headers.put("Authorization", "Bearer" + response.body().asString());

    Response responseOrders = given().
            headers(headers).
            when().
            get("http://51.250.6.164:8080/orders").
            then().
            statusCode(200).extract().response();

        System.out.println(responseOrders.body().asString());

    }



    @Test
    public void getTest () {
        when().
                get (urlSwagger+"/test-orders/{id}",8).
        then().
                statusCode(200).
                body("status", equalTo("OPEN"),
                        "courierId", equalTo(null),
                       // "customerName", equalTo("Samuel"),
                       // "customerPhone", equalTo("+37255544422"),
                       // "comment", equalTo("hello ZZWfI"),
                        "id",equalTo(8));

    }
    @Test
    public void postOrder(){
        Order requestOrder = new Order();

        requestOrder.setStatus("OPEN");
        requestOrder.setCustomerName("Samuel");
        requestOrder.setCourierId(432L);
        requestOrder.setCustomerPhone("5564879");
        requestOrder.setComment("Hey guys");

        TestOrderFunctions testOrderFunctions = new TestOrderFunctions();

        Order responseOrder = testOrderFunctions.postNewOrder(requestOrder);

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




    }

}
