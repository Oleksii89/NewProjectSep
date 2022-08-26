package web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseWebTests {
    @BeforeAll
    public static void setup () {
        Configuration.baseUrl = "http://51.250.6.164:3000";
    }
}
