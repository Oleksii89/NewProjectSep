package api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class NewTests2 {
    private String urlSwagger = "http://51.250.6.164:8080";

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
}
