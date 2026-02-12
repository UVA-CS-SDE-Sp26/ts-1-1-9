/**
 * Commmand Line Utility
 * part a --> command line interface
 * checking the number of arguments the user uses
 */
import java.util.regex.Pattern;

public class TopSecret {
    //defines a constant Pattern in Java that matches exactly two digits (like "01", "12", "99").
    private static final Pattern FILE_NUMBER = Pattern.compile("\\d{2}");

    //Testing variable
    //0 = list files
    //1 = display file with default key
    //2 = display file with provided key
    //3 = invalid arguments
    private static int testing = -1;

    public int getTesting(){
        return testing;
    }

    public void setTesting(int testing){
        TopSecret.testing = testing;
    }

    public static void main(String[] args) {
        ProgramController user = new ProgramController();

        try{
            validateArgs(args);

            //if there are no arguments, list the numbered files available to display
            if (args.length == 0){
                user.listFiles();
                testing = 0;
            }
            //if there is one argument, open the file at that index (use the default key)
            else if (args.length == 1){
                user.displayFile(args[0],null);
                testing = 1;
            }
            //if two args, display the file at the index of the first argument and the key provided by the second argument
            else{ //after validating, the else would just be when args.length == 2
                user.displayFile(args[0],args[1]);
                testing = 2;
            }

        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Correct Format: java topsecret [fileNumber] [optionalKey]");
            testing = 3;
        }
    }

    //VALIDATING ARGUMENTS
    static void validateArgs(String[] args){
        //only 0,1,2 arguments allowed
        if (args.length > 2){
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        //validate file number if provided
        if (args.length >= 1){
            String fileNumber = args[0];
            if (fileNumber == null || !FILE_NUMBER.matcher(fileNumber).matches()){
                throw new IllegalArgumentException("Invalid file number. Use two digits like 01.");
            }
        }

        //validate cipherkey if provided
        if (args.length == 2){
            String optionalKey = args[1];
            if (optionalKey == null || optionalKey.isBlank()){
                throw new IllegalArgumentException("Invalid optional key. Optional Cipher key can't be blank.");
            }
        }
    }
}


