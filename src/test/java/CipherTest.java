// import statements
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * commented tests:
 * cipherMockitoTest class simulates the cipher object using a simple mocking test
 * cipherMockitoTest class mocks the cipher decipher method
 * the above tests have been commented out because cipher does not contain
 * concrete dependencies to test
 *
 * actual tests:
 * loadKey method is tested against the file not found error and returns the file not
 * found method when an invalid file path is delivered as a parameter to the behavior
 * decipher method is tested against same and different sized, invalid and valid,
 * and different combinations of the global cipher and actual string variables for correctness
 * the accessor methods for both global variables are tested for correctness using the mutator methods
 *
 * references:
 * please note that Microsoft Copilot was used to streamline test creation process
 * by manual input of strategies and different test design frameworks to simulate
 * dependencies and objects using IntelliJ IDEA and Mockito test framework as well
 * as suggest which tests would be most helpful to show the functionality
 * of cipher's behaviors and attributes
 * @author Aaryav Walter, references as cited above including Microsoft Copilot
 * @version 1.0
 */
//@ExtendWith(MockitoExtension.class)
class CipherTest {
/*
    @Test
    void spy_callsRealDecipher_andIsVerifiedByMockito() {
        // instantiate cipher object
        Cipher realCipher = new Cipher();

        // ppy wraps the real object
        Cipher spyCipher = Mockito.spy(realCipher);

        // set internal state using your test-only setters
        spyCipher.setActual("abc");
        spyCipher.setCipher("xyz");

        // cipher decipher method called
        String result = spyCipher.decipher("xzy", "xyz");

        // test result and verification
        assertEquals("acb", result);
        verify(spyCipher).decipher("xzy", "xyz");
    }
*/

    @Test
    void loadKeyOne() {
        Cipher loadKeyOne = new Cipher();
        assertThrows(FileNotFoundException.class, () -> {
            loadKeyOne.loadKey("this_file_does_not_exist.txt");
        });
    }

    @Test
    void loadKeyTwo() {
        Cipher loadKeyTwo = new Cipher();
        assertThrows(FileNotFoundException.class, () -> {
            loadKeyTwo.loadKey("");
        });
    }

    @Test
    void decipher_successCase() {
        Cipher cipher = new Cipher();
        cipher.setActual("abcdefghijklmnopqrstuvwxyz");
        cipher.setCipher("cdefghijklmnopqrstuvwxyzab");
        assertEquals("hello", cipher.decipher("jgnnq", cipher.getCipher()));
    }

    @Test
    void decipher_lengthMismatch_returnsNull() {
        Cipher cipher = new Cipher();
        cipher.setActual("abcdefghijklmnopqrstuvwxyz");
        cipher.setCipher("cdefghijklmnopqrstuvwxyzabr"); // length 27 vs 26
        assertNull(cipher.decipher("greetings", cipher.getCipher()));
    }

    @Test
    void decipher_emptyActualAndEmptyCipher_returnsEmptyString() {
        Cipher cipher = new Cipher();
        cipher.setActual("");          // actual = ""
        // cipher is "" by default
        assertEquals("", cipher.decipher("sun", cipher.getCipher()));
    }

    @Test
    void decipher_emptyCipherWithNonEmptyActual_returnsNull() {
        Cipher cipher = new Cipher();
        cipher.setActual("abcdefghijklmnopqrstuvwxyz"); // length 26
        // cipher is "" -> length 0, mismatch
        assertNull(cipher.decipher("value", cipher.getCipher()));
    }

    @Test
    void decipher_nonEmptyCipherWithEmptyActual_returnsNull() {
        Cipher cipher = new Cipher();
        cipher.setCipher("cdefghijklmnopqrstuvwxyzab"); // length 26
        // actual is "" -> length 0, mismatch
        assertNull(cipher.decipher("anything", cipher.getCipher()));
    }

    @Test
    void getCipher() {
        // test one
        Cipher getCipherOne = new Cipher();
        getCipherOne.setCipher("amazing");
        assertEquals("amazing", getCipherOne.getCipher(), "return 'amazing'");

        // test two
        Cipher getCipherTwo = new Cipher();
        assertEquals("", getCipherTwo.getCipher(), "return empty string");
    }

    @Test
    void getActual() {
        // test one
        Cipher getActualOne = new Cipher();
        getActualOne.setActual("learning");
        assertEquals("learning", getActualOne.getActual(), "return 'learning'");

        // test two
        Cipher getActualTwo = new Cipher();
        assertEquals("", getActualTwo.getActual(), "return empty string");
    }

    /*
    // mock cipher object
    @Mock
    Cipher cipher;
    // use stubbed value to simulate cipher
    @Test
    void mock_decipher_returnsStubbedValue() {
        // stub behavior
        when(cipher.decipher("abc", "xyz")).thenReturn("hello");

        // test/mock decipher attribute
        String result = cipher.decipher("abc", "xyz");

        // test result and verification
        assertEquals("hello", result);
        verify(cipher).decipher("abc", "xyz");
    }
     */
}
