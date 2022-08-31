package api;

import com.google.gson.Gson;
import dto.Order;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class OrderFunctions {
    private String baseUrl;

    public OrderFunctions() {
        try(InputStream input = new FileInputStream("src/main/resources/settings.properties")) {
            Properties prop = new Properties();

            //load a properties file
            prop.load(input);
            baseUrl = prop.getProperty("baseURL");


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public Order postNewOrder (Order body, Map<String,String> headers) {

        Gson gson = new Gson();
        String stringRequestOrder = gson.toJson(body);


        Response response = given().
                headers(headers).
                body(stringRequestOrder).
                when().
                post(baseUrl+"/orders").
                then().
                statusCode(200).extract().response();

        return gson.fromJson(response.body().asString(), Order.class);

    }
    public String postNewOrder  (Order body, Integer statusCode, Map<String,String> headers) {
        Gson gson = new Gson();
        String stringRequestOrder = gson.toJson(body);

        Response response = given().
                headers(headers).
                body(stringRequestOrder).
                when().
                post(baseUrl+"/orders").
                then().
                statusCode(statusCode).extract().response();

        return response.body().asString();

    }

    public Order putOrderToAssign(Integer orderId, Map<String,String> headers) {
        Gson gson = new Gson();


        Response response = given().
                headers(headers).
                pathParam("id", orderId).
                when().
                put(baseUrl+"/orders/{id}/assign").
                then().
                statusCode(200).extract().response();

        return gson.fromJson(response.body().asString(), Order.class);

    }
    public Order putOrderToInprogress(Integer orderId, Map<String, String> headers) {

        Gson gson = new Gson();
        String stringRequestStatus = "{\n" +
                " \"status\": \"INPROGRESS\"\n" +
                "}";

        Response response = given().
                headers(headers).
                pathParam("id", orderId).
                body(stringRequestStatus).
                when().
                put(baseUrl + "/orders/{id}/status").
                then().
                statusCode(200).extract().response();

        return gson.fromJson(response.body().asString(), Order.class);
    }
    public Order putOrderToDelivered(Integer orderId, Map<String, String> headers) {

        Gson gson = new Gson();
        String stringRequestStatus = "{\n" +
                " \"status\": \"DELIVERED\"\n" +
                "}";

        Response response = given().
                headers(headers).
                pathParam("id", orderId).
                body(stringRequestStatus).
                when().
                put(baseUrl + "/orders/{id}/status").
                then().
                statusCode(200).extract().response();

        return gson.fromJson(response.body().asString(), Order.class);

    }

}
