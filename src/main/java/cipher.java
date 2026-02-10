import java.io.File;
import java.util.Scanner;

public class cipher {
    public static void main(String[] args) throws Exception
    {
        String result = decipher("cat", "key1");
        System.out.println(result);
    }

    public static String decipher(String cipherString) throws Exception
    {
        File keyFile = new File("ciphers/key.txt");
        Scanner fileReader = new Scanner(keyFile);

        String uncipheredValues = "";
        String cipherKey = "";

        boolean validation;

        if (fileReader.hasNext()){
            uncipheredValues = fileReader.nextLine();
        }
        if (fileReader.hasNext()){
            cipherKey = fileReader.nextLine();
        }

        validation = validate(cipherString, cipherKey, uncipheredValues);

        if(!validation){
            return "failed validation";
        }

        return decipherString(cipherString, cipherKey, uncipheredValues);
    }

    public static String decipher(String cipherString, String key) throws Exception
    {
        String keyFilePath;

        if(!key.toLowerCase().endsWith(".txt")){
            keyFilePath = "ciphers/"+key+".txt";
        }
        else{
            keyFilePath = "ciphers/"+key;
        }

        File keyFile = new File(keyFilePath);

        if (!keyFile.exists()) {
            return ("File: " + key + " Not Found");
        }

        Scanner fileReader = new Scanner(keyFile);


        String uncipheredValues = "";
        String cipherKey = "";

        boolean validation;

        if (fileReader.hasNext()){
            uncipheredValues = fileReader.nextLine();
        }
        if (fileReader.hasNext()){
            cipherKey = fileReader.nextLine();
        }

        validation = validate(cipherString, cipherKey, uncipheredValues);

        if(!validation){
            return "failed validation";
        }

        return decipherString(cipherString, cipherKey, uncipheredValues);
    }


    public static boolean validate(String cipherString, String cipherKey, String uncipheredValues){ // validation checker
        if((cipherString.isEmpty()) || (uncipheredValues.isEmpty())){
            return false;
        }
        if(cipherKey.length() != uncipheredValues.length()){
            return false;
        }
        for(int x = 0; x < cipherString.length(); x++){
            boolean check = false;
            for(int i = 0; i < cipherKey.length(); i++){
                if(cipherString.charAt(x) == cipherKey.charAt(i)) {
                    check = true;
                    break;
                }
            }
            if(!check){
                return false;
            }
        }
        return true;
    }

    public static String decipherString(String cipherString, String cipherKey, String uncipheredValues){
        String decipheredString = "";
        for(int x = 0; x < cipherString.length(); x++){
            for(int i = 0; i < cipherKey.length(); i++){
                if(cipherString.charAt(x) == cipherKey.charAt(i)){
                    decipheredString += uncipheredValues.charAt(i);
                }
            }
        }
        return decipheredString;
    }
}
