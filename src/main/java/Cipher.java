import java.io.File;
import java.util.Scanner;
import java.util.Objects;

public class Cipher {
    public static void main(String[] args) throws Exception
    {
        String result = decipher();
        System.out.println(result);
    }

    public static String decipher() throws Exception
    {
        File keyFile = new File("ciphers/key.txt");
        Scanner fileReader = new Scanner(keyFile);

        String actualValues = "";
        String cipherMatch = "";
        String cipheredString = "";

        boolean validation;
        String result;

        if (fileReader.hasNext()){
            actualValues = fileReader.next();
            cipherMatch = fileReader.next();
        }

        validation = validate(cipheredString, cipherMatch);
        if(!validation){
            return null;
        }
        else
        {
            result = decipherString(cipheredString, cipherMatch, actualValues);
        }
        return result;
    }

    public static boolean validate(String stringText, String cipherValues){ // validation checker
        for(int x = 0; x < stringText.length(); x++){
            boolean check = false;
            for(int i = 0; i < cipherValues.length(); i++){
                if(stringText.charAt(x) == cipherValues.charAt(i)) {
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

    public static String decipherString(String stringText, String cipherValues, String decipherValues){
        String decipheredText = stringText;
        for(int x = 0; x < stringText.length(); x++){
            for(int i = 0; i < cipherValues.length(); i++){
                if(stringText.charAt(x) == cipherValues.charAt(i)){
                    decipheredText = decipheredText.replace(stringText.charAt(x), decipherValues.charAt(i));
                }
            }
        }
        return decipheredText;
    }
}
