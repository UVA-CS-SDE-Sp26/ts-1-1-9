import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler {

    public FileHandler() {}

    /** Reads a file from the data folder and returns its contents. */
    public String readDataFile(String filename) throws IOException {
        if (filename == null || filename.isBlank()) {
            throw new IllegalArgumentException("Filename must not be null or blank.");
        }

        Path path = Path.of("data", filename);
        return Files.readString(path);
    }
}