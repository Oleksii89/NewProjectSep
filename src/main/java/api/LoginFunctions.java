package api;

import com.google.gson.Gson;
import dto.Login;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;


public class LoginFunctions {
    private String baseUrl;

    public LoginFunctions() {
        try (InputStream input = new FileInputStream("src/main/resources/settings.properties")) {
            Properties prop = new Properties();

            //load a properties file
            prop.load(input);
            baseUrl = prop.getProperty("baseURL");


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public String loginAsStudent(String username, String password){
        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);

        Gson gson = new Gson();
        String stringRequestLogin = gson.toJson(login);

        Response response = given().
                headers("Content-type", "application/json").
                body(stringRequestLogin).
                when().
                post(baseUrl+"/login/student").
                then().
                statusCode(200).extract().response();

        return "Bearer " + response.body().asString();


    }


}
