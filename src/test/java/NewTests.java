import com.google.gson.Gson;
import dto.Order;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;


public class NewTests {
    private String urlSwagger = "http://51.250.6.164:8080";
    @Test
    public void catVoiceTest() {
        Cat Zombik = new Cat();

        assertEquals("Meow-Meow", Zombik.voiceCat, "Cat doesn't say anything");
    }

    @Test
    public void dogVoiceTest() {
        Dog Betty = new Dog();

        assertEquals("Gav-Gav", Betty.voiceDog, "Dog is silent");
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", useHeadersInDisplayName = true)
    void testWithFileSourceAndHeaders(String Login, String Password, String Role) {
        Assertions.assertNotNull(Login);
        Assertions.assertNotEquals("", Password);
        Assertions.assertNotEquals("", Role);
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

        Gson gson = new Gson();
        String stringRequestOrder = gson.toJson(requestOrder);

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

        Response response = given().
                header("Content-type", "application/json").
                body(stringRequestOrder).
                when().
                post(urlSwagger+"/test-orders").
                then().
                statusCode(200).extract().response();

        Order responseOrder = gson.fromJson(response.body().asString(), Order.class);

        Assertions.assertEquals(responseOrder.getStatus(), "OPEN", "status error");
        Assertions.assertEquals(responseOrder.getCustomerName(), "Samuel", "incorrect name");
        Assertions.assertEquals(responseOrder.getCourierId(),432, "incorrect courier ID");
        Assertions.assertEquals(responseOrder.getCustomerPhone(),"5564879", "incorrect customer phone");
        Assertions.assertEquals(responseOrder.getComment(), "Hey guys", "incorrect comment");

        System.out.println(responseOrder.toString());




    }

}
