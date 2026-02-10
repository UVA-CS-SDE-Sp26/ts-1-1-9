import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @Test
    public void readDataFile_invalidFilename() {
        FileHandler handler = new FileHandler();

        assertThrows(IllegalArgumentException.class, () -> handler.readDataFile(null));
        assertThrows(IllegalArgumentException.class, () -> handler.readDataFile(""));
        assertThrows(IllegalArgumentException.class, () -> handler.readDataFile("   "));
    }
}
