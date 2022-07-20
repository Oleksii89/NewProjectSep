import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

public class NewTests {
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
}
