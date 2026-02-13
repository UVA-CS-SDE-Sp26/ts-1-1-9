// Daniel Louie (tmd3bj)
// Program Control

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProgramControl {
    // where the ciphered text to decrypt is
    private static final String FILES_FOLDER = "src" + File.separator + "files";
    // where cipher key files are
    private static final String CIPHER_FOLDER = "ciphers";
    // default key in ciphers folder
    private static final String DEFAULT_KEY_FILE = "key.txt";

    // Called when program runs with no arguments.
    // Prints the numbered list: 01 filea.txt   02 fileb.txt    ...
    public static void printFileList() {
        List<String> files = listDataFiles();
        if (files.isEmpty()) {
            System.out.println("No files found in data folder");
            return;
        }

        for (int i  = 0; i < files.size(); i++) {
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
    public static void displayFile(String fileId, String keyArgName) {
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
        String filePath = FILES_FOLDER + File.separator + filename;

        // Use Team Member B: read file
        fileHandler fh = new fileHandler(filePath);
        String cipheredText = fh.fileOut();

        if (cipheredText == null || cipheredText.isBlank()) {
            System.out.println("Error: Could not read file: " + filePath);
            return;
        }

        // Read key data
        fileHandler keyFH = new fileHandler(keyFilePath);
        String keyData = keyFH.fileOut();

        if (keyData == null || keyData.isBlank()) {
            System.out.println("Error: Could not read key file: " + filePath);
            return;
        }

        // Team Member D: decipher using ciphered string and cipher key string
        try {
            String readableText = cipher.decipher(cipheredText, keyData);
            System.out.println(readableText);
        }
        catch (Exception e){
            System.out.println("Error: Could not decipher file");
        }
    }

    // Helper method for printFileList()
    // Lists files in data folder and returns them sorted (alphabetical)
    public static List<String> listDataFiles() {
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
    public static int parseFileIdToIndex(String fileId) {
        if (fileId == null || fileId.length() != 2)
            return -1;
        if (!Character.isDigit(fileId.charAt(0)) || !Character.isDigit(fileId.charAt(1)))
            return -1;

        int number = Integer.parseInt(fileId); // "01" -> 1
        return number - 1; // convert to 0-based index
    }

}