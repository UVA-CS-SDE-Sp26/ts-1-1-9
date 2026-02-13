// Henry Fong (yks8nq)
// Cipher Program

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class cipher {
    // decipher method
    public static String decipher(String cipherString, String cipherKeyData) throws Exception
    {
        boolean validation;
        ArrayList<String> resultData = new ArrayList<>(); // Creates a vector list to store cipher data if the data is multiple lines
        String[] cipherData = cipherKeyData.split("\n"); // Splits the cipher key data into multiple strings based on "\n"

        //Check to see if cipherData has 2 lines
        if (cipherData.length < 2){
            return "Failed Validation";
        }

        String uncipheredValues = cipherData[0]; // assign first line of key data to uncipheredValues
        String cipherKey = cipherData[1]; // assign second line of key data to cipherKey

        // Check to see if cipherString is multi line or single line
        if(cipherString.contains("\n")){
            String[] cipherStringData = cipherString.split("\n"); // if cipherString is multi line split based on "\n" and store in cipherStringData

            // Iterates through each line in cipherStringData
            for(String cipherLines : cipherStringData){
                validation = validate(cipherLines, cipherKey, uncipheredValues); // calls validate method to check if the cipher string and data in txt file is valid

                // if validation is false then add failed validation to resultData
                if(!validation){
                    resultData.add("Failed validation");
                }
                else {
                    resultData.add(decipherString(cipherLines, cipherKey, uncipheredValues)); // add deciphered line to resultData
                }

            }
            return String.join("\n", resultData); // return resultData back as string
        }

        // if cipherString is not multiple lines will instead go through this and return a single line of data
        validation = validate(cipherString, cipherKey, uncipheredValues); // calls validate method to check if the cipher string and data in txt file is valid

        // if validation is false then return failed validation
        if(!validation){
            return "Failed validation";
        }

        return decipherString(cipherString, cipherKey, uncipheredValues); // if validation is true then will return deciphered string using decipherString method
    }


    public static boolean validate(String cipherString, String cipherKey, String uncipheredValues){ // validation checker
        String specialCharacters = "!@#$%^&*()-_+=[]{}|\\\"';:/?><.,`~"; // string that contains special characters that is allowed to pass the validation

        // if cipherString is empty will fail validation
        if(cipherString.isBlank()){
            return false;
        }

        // if cipherKey and uncipheredValues length are not the same it will fail validation
        if(cipherKey.length() != uncipheredValues.length()){
            return false;
        }

        // check to see if all values in cipherString matches with a value in cipherKey
        for(int x = 0; x < cipherString.length(); x++){ // for loop to check each individual value in cipherString
            boolean check = false;
            for(int i = 0; i < cipherKey.length(); i++){ // iterates through each individual value in cipherKey
                // checks if string has special character or space if they do allow them to pass validation
                if((cipherString.charAt(x) == cipherKey.charAt(i)) || (cipherString.charAt(x) == ' ') || (specialCharacters.contains(String.valueOf(cipherString.charAt(x)))))  { // if the value of cipherString is equal to value of cipherKey break out of the for loop and change check to true
                    check = true;
                    break;
                }
            }

            // if check is false will return false
            if(!check){
                return false;
            }
        }

        // if fully iterates through the cipherString and all characters matched with a value in cipherKey return true
        return true;
    }

    // decipherString method
    public static String decipherString(String cipherString, String cipherKey, String uncipheredValues){
        String decipheredString = "";
        String specialCharacters = "!@#$%^&*()-_+=[]{}|\\\"';:/?><.,`~";


        // for loop to match cipherString value with a cipherKey value
        for(int x = 0; x < cipherString.length(); x++){
            for(int i = 0; i < cipherKey.length(); i++){
                if(cipherString.charAt(x) == ' '){ // add spaces
                    decipheredString += ' ';
                    break;
                }
                if (specialCharacters.contains(String.valueOf(cipherString.charAt(x)))){ // add special characters
                    decipheredString += cipherString.charAt(x);
                    break;
                }
                if((cipherString.charAt(x) == cipherKey.charAt(i))){ // if cipherKey and cipherString match add uncipheredValue into decipheredString to get the correct string
                    decipheredString += uncipheredValues.charAt(i);
                    break;
                }
            }
        }
        return decipheredString;
    }
}
