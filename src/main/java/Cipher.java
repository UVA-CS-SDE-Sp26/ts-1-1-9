// import appropriate libraries
import java.io.FileNotFoundException;
import  java.util.Scanner;
import  java.util.*;
import java.io.File;

/**
 * the cipher class contains two global variables that contain the actual character
 * and cipher key strings that are used along with a scanner to decipher user texts
 * that are provided as parameters into class behaviors
 * the cipher class contains two global variables, one empty constructor, and
 * two behaviors
 * please note that the behaviors must be called chronologically
 * please note that Microsoft Copilot (minimally) was used to optimize the running
 * time and complexity of the decipher behavior in the Cipher class
 * @author Aaryav Walter, references as cited
 * @version 1.0
 */
public class Cipher {
    // declare global variables
    // actual character and cipher key strings
    private String actual;
    private String cipher;

    // empty constructor
    public Cipher() {
        // global variables
        this.cipher  = "";
        this.actual = "";
    }

    /**
     * method receives file path of cipher key as a string
     * method updates global variables with file contents
     * method output intended as one of two parameters for following method
     *
     * @param keyFilePath
     * @return cipher key
     * @throws FileNotFoundException
     */


    /*
    public String loadKey(String keyFilePath) {
        try {
            // initialize new file object using string parameter
            File cipherKeyFile = new File(keyFilePath);
            // initialize new scanner object to read file
            Scanner recordCipherStrings = new Scanner(cipherKeyFile);
            // check whether file has next line
            if(recordCipherStrings.hasNextLine()) {
                // first line is the actual character string
                this.actual = recordCipherStrings.nextLine();
                // check whether file has second line
                if (recordCipherStrings.hasNextLine()) {
                    // second line is the cipher character key string
                    this.cipher = recordCipherStrings.nextLine();
                    recordCipherStrings.close();
                } else {
                    // file does not contain a cipher key
                    recordCipherStrings.close();
                    // warning statement
                    System.out.println("The file does not contain a cipher key.");
                }
            } else {
                // file is empty
                recordCipherStrings.close();
                // warning statement
                System.out.println("The file is empty.");
            }
        } catch(FileNotFoundException e) {
            // error statement
            System.out.println("Error. The file was not found.");
            e.printStackTrace();
        }
        return null;
    }
    */

    public String loadKey(String keyFilePath) throws FileNotFoundException {
        // initialize new file object using string parameter
        File cipherKeyFile = new File(keyFilePath);
        // initialize new scanner object to read file
        Scanner recordCipherStrings = new Scanner(cipherKeyFile);

        // check whether file has next line
        if (recordCipherStrings.hasNextLine()) {
            // first line is the actual character string
            this.actual = recordCipherStrings.nextLine();
            // check whether file has second line
            if (recordCipherStrings.hasNextLine()) {
                // second line is the cipher character key string
                this.cipher = recordCipherStrings.nextLine();
            } else {
                recordCipherStrings.close();
                System.out.println("The file does not contain a cipher key.");
            }
        } else {
            recordCipherStrings.close();
            System.out.println("The file is empty.");
        }

        recordCipherStrings.close();
        return this.cipher;
    }

    /*
    public String loadKey(String keyFilePath) {
        // initialize new file object using string parameter

        File cipherKeyFile = new File(keyFilePath);
        // initialize new scanner object to read file
        Scanner recordCipherStrings = new Scanner(cipherKeyFile);

        // check whether file has next line
        if (recordCipherStrings.hasNextLine()) {
            // first line is the actual character string
            this.actual = recordCipherStrings.nextLine();
            // check whether file has second line
            if (recordCipherStrings.hasNextLine()) {
                // second line is the cipher character key string
                this.cipher = recordCipherStrings.nextLine();
            } else {
                recordCipherStrings.close();
                System.out.println("The file does not contain a cipher key.");
            }
        } else {
            recordCipherStrings.close();
            System.out.println("The file is empty.");
        }

        recordCipherStrings.close();
        return this.cipher;
    }
    */

    /**
     * method receives user scanner input and cipher key both as strings
     * method maps cipher key characters to actual characters with global variables
     * method iterates through user text and replaces characters using mapped values
     * method returns deciphered user text on next line of terminal
     *
     * @param userText
     * @param cipherKey
     * @return deciphered user text
     */
    public String decipher(String userText, String cipherKey) {
        // update cipher key global variable based on parameter
        this.cipher = cipherKey;

        // cipher key and actual character strings must match length
        if (this.cipher.length() != this.actual.length()) {
            // warning statements
            System.out.println("The cipher key and actual character strings have different lengths.");
            System.out.println("Modify one or both to be the same length.");
            return null;
        }
        // use hash map to map cipher key values to actual character values
        Map<Character, Character> mapKeyCharacter = new HashMap<>();
        for (int i = 0; i < this.cipher.length(); i++) {
            mapKeyCharacter.put(this.cipher.charAt(i), this.actual.charAt(i));
        }
        // initialize string builder object
        StringBuilder deciphered = new StringBuilder();
        // iterate through user scanner input and update each character
        for (int i = 0; i < userText.length(); i++) {
            char ch = userText.charAt(i);
            // characters not in the actual character string such
            // as punctuation symbols are automatically dropped
            if (mapKeyCharacter.containsKey(ch)) {
                deciphered.append(mapKeyCharacter.get(ch));
            }
        }
        // prompt next line
        System.out.println();
        // return deciphered user text
        return deciphered.toString();
    }

    /**
     * cipher key accessor method
     * @return cipher key
     */
    public String getCipher() {
        return this.cipher;
    }

    /**
     * actual character string accessor method
     * @return actual character string
     */
    public String getActual() {
        return this.actual;
    }

    /**
     * mutator method solely for test cases
     * @param userCipher
     */
    public void setCipher(String userCipher) {
        this.cipher  = userCipher;
    }

    /**
     * mutator method solely for test cases
     * @param userActual
     */
    public void setActual(String userActual) {
        this.actual  = userActual;
    }

}
