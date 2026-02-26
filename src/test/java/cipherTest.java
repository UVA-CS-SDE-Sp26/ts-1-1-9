import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;

public class cipherTest {

    @Test
    void testNonEqualKeyLength() throws Exception{
        String result = cipher.decipher("Test", "Absbiasdha\ndjahjdisajkj");
        assertEquals("Failed validation", result, "Should return \"Failed validation\"");
    }

    @Test
    void testNonExistentKey() throws Exception{
        String result = cipher.decipher("Test", "adksjkasjj\n  ");
        assertEquals("Failed validation", result, "Should return \"Failed validation\"");
    }

    @Test
    void testNonExistentCipherString() throws Exception{
        String result = cipher.decipher("", "ABCD\nBCDA");
        assertEquals("Failed validation", result, "Should return \"Failed validation\"");
    }

    @Test
    void testCipherStringContainsUnknownValue() throws Exception{
        String result = cipher.decipher("eph", "ABCD\nBCDA");
        assertEquals("Failed validation", result, "Should return \"Failed validation\"");
    }

    @Test
    void decipherReturnCorrect() throws Exception{
        String result = cipher.decipher("eph", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890\nbcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a");
        assertEquals("dog", result, "Should return \"dog\"");
    }

    @Test
    void decipherReturnMultipleCorrect() throws Exception{
        String result = cipher.decipher("eph\neph\n eph", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890\nbcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a");
        assertEquals("dog\ndog\n dog", result, "should return dog\ndog\n dog");
    }

    @Test
    void testEmptyCipherStringWithSpace() throws Exception{
        String result = cipher.decipher("    ", "ABCD\nBCDA");
        assertEquals("Failed validation", result, "Should return \"Failed validation\"");
    }

    @Test
    void testCipherStringIncludingSpace() throws Exception{
        String result = cipher.decipher("eph      eph", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890\nbcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a");
        assertEquals("dog      dog", result, "Should return dog      dog");
    }

    @Test
    void testIfWillReturnTrueAndInvalidData() throws Exception{
        String result = cipher.decipher("eph\n cat", "dog\neph");
        assertEquals("dog\nFailed validation", result, "Should return dog\n Failed validation");
    }
}

