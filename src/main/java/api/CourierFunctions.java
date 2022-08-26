package api;

import com.google.gson.Gson;
import dto.Courier;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class CourierFunctions {
    private String baseUrl;
    public CourierFunctions() {
        try(InputStream input = new FileInputStream("src/main/resources/settings.properties")) {
            Properties prop = new Properties();

            //load a properties file
            prop.load(input);
            baseUrl = prop.getProperty("baseURL");


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public Courier postNewCourier (Courier body, Map<String,String> headers) {

        Gson gson = new Gson();
        String stringRequestCourier = gson.toJson(body);


        Response response = given().
                headers(headers).
                body(stringRequestCourier).
                when().
                post(baseUrl+"/users/courier").
                then().
                statusCode(200).extract().response();

        return gson.fromJson(response.body().asString(), Courier.class);

    }
    public String postNewCourier  (Courier body, Integer statusCode, Map<String,String> headers) {
        Gson gson = new Gson();
        String stringRequestCourier = gson.toJson(body);

        Response response = given().
                headers(headers).
                body(stringRequestCourier).
                when().
                post(baseUrl+"/users/courier").
                then().
                statusCode(statusCode).extract().response();

        return response.body().asString();

    }


}
