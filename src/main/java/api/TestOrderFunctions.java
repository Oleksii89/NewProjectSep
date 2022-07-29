package api;

import com.google.gson.Gson;
import dto.Order;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static io.restassured.RestAssured.given;

public class TestOrderFunctions {

    private String baseUrl;

    public TestOrderFunctions() {
        try(InputStream input = new FileInputStream("src/main/resources/properties")) {
            Properties prop = new Properties();

            //load a properties file
            prop.load(input);
            baseUrl = prop.getProperty("baseURL");


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public Order postNewOrder (Order body) {
        Gson gson = new Gson();
        String stringRequestOrder = gson.toJson(body);

        Response response = given().
                headers("Content-type", "application/json").
                body(stringRequestOrder).
                when().
                post(baseUrl+"/test-orders").
                then().
                statusCode(200).extract().response();

        return gson.fromJson(response.body().asString(), Order.class);

    }
    public String postNewOrder (Order body, Integer statusCode) {
        Gson gson = new Gson();
        String stringRequestOrder = gson.toJson(body);

        Response response = given().
                headers("Content-type", "application/json").
                body(stringRequestOrder).
                when().
                post(baseUrl+"/test-orders").
                then().
                statusCode(statusCode).extract().response();

        return response.body().asString();

    }
}
