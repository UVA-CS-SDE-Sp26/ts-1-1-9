import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CipherTest {
    @Test
    public void addTest() {
        Assertions.assertEquals(4, Cipher.add(1,3));
    }
}