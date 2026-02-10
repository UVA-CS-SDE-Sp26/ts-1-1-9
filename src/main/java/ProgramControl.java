// Daniel Louie (tmd3bj)
// Program Control

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProgramControl {
    private static final String DATA_FOLDER = "data";
    private static final String DEFAULT_KEY_FILE = "ciphers/key.txt";

    // Called when program runs with no arguments.
    // Prints the numbered list: 01 filea.txt   02 fileb.txt    ...
    public static void printFileList() {
        List<String> files = listDataFiles();
        if (files.isEmpty()) {
            System.out.println("No files found in data folder");
            return;
        }

        for (int i = 0; i < files.size(); i++) {
            System.out.printf("%02d %s%n", i + 1, files.get(i));
        }
    }

    // Called when program runs with 1 arg (default key)
    // Example: java TopSecret 01
    public static void displayFile(String fileId) {
        displayFile(fileId, null);
    }

    // Called when program runs with 2 args (custom key)
    // Example: java TopSecret 01 customkey.txt
    public static void displayFile(String fileId, String keyArgPath) {
        List<String> files = listDataFiles();
        if (files.isEmpty()) {
            System.out.println("Error: No files available.");
            return;
        }

        int index = parseFileIdToIndex(fileId); // get proper index of file
        if (index < 0 || index >= files.size()) {
            System.out.println("Error: Invalid file number: " + fileId);
            System.out.println("Available files:");
            printFileList();
            return;
        }

        // Building path to selected file
        String filename = files.get(index);
        String filePath = DATA_FOLDER + File.separator + filename;

        // Use Team Member B: read file
        fileHandler fh = new fileHandler(filePath);
        String cipheredText = fh.fileOut();

        if (cipheredText == null) {
            System.out.println("Error: Could not read file: " + filePath);
            return;
        }

        // Team Member D: decipher using key file
        String keyFilePath;
        if (keyArgPath == null || keyArgPath.isBlank()) {
            keyFilePath = DEFAULT_KEY_FILE;
        } else {
            keyFilePath = keyArgPath;
        }
        
        // Get Cipher class method to take in two parameters
        String readableText = Cipher.decipher(cipheredText, keyFilePath);

        if (readableText == null) {
            System.out.println("Error: Cipher validation failed (text contains characters not in cipher key).");
            return;
        }

        System.out.println(readableText);
    }

    // Helper method for printFileList()
    // Lists files in data folder and returns them sorted (alphabetical)
    private static List<String> listDataFiles() {
        File folder = new File(DATA_FOLDER);
        File[] files = folder.listFiles();

        List<String> names = new ArrayList<>();
        if (files == null)
            return names;

        for (File f : files) {
            if (f.isFile()) {
                names.add(f.getName());
            }
        }

        Collections.sort(names);
        return names;
    }

    // Helper method to convert "01" -> 0, "02" -> 1, etc...
    // Returns -1 if invalid
    private static int parseFileIdToIndex(String fileId) {
        if (fileId == null || fileId.length() != 2)
            return -1;
        if (!Character.isDigit(fileId.charAt(0)) || !Character.isDigit(fileId.charAt(1)))
            return -1;

        int number = Integer.parseInt(fileId); // "01" -> 1
        return number - 1; // convert to 0-based index
    }

}
