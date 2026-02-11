/**
 * Commmand Line Utility
 * part a --> command line interface
 * checking the number of arguments the user uses
 */
import java.util.*;
import java.io.*;
public class TopSecret {
    public static File folder;

    public TopSecret(String s) {
        folder = new File("./" + s); // this is supposed to read in a FOLDER
    }

    public String programControl(String[] args, File[] listOfFiles)
    {
        if (args.length == 0) // if no arguments are provided
        {
            for (int i = 0; i < listOfFiles.length; i++) // iterate through each file in the folder
            {
                System.out.println(i + " " + listOfFiles[i]); // print file index and file name
            }
            return "EXIT!";
            //System.exit(0); // exit
        }

        else if(args.length == 1) // if one argument is provided
        {
            try{
                int index = Integer.parseInt(args[0]); // try to get the argument as an integer
                if(index < 0 || index >= listOfFiles.length) // if the index is out of bounds
                {
                    System.out.println("Invalid index"); // error
                    return "EXIT!";
                    //System.exit(0); // exit
                }
                else { // if the index is within bounds of the number of files
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(listOfFiles[index])); // creates a buffered file reader for the file at that specific index
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line); // print contents of file
                        }
                        br.close();
                        return "EXIT!";
                        //System.exit(0); // exit
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            catch (NumberFormatException e){ // if a number is not entered
                System.out.println(args[0] + " is not a valid number");
            }
        }
        else{ // neither 0 nor 1 args are given
            // assume checking for encryption/decryption goes here?
        }
        return "EXIT!";
    }

    public static void main(String[] args) { // maybe this should be in a "solve" method b/c we are running tests in a separate file?
        //File folder = new File("data"); // just replace this with whatever the actual folder is (especially file and whatnot)

        // Team member A

        // Team member B

        // Team member C
        TopSecret s = new TopSecret("data");
        File[] listOfFiles  = folder.listFiles(); // array of Files (dependent on stuff b4)
        if(listOfFiles != null) { // if there exists files in the folder

            try {
                String answer = s.programControl(args, listOfFiles);
                System.out.println(answer);
            }
            catch (Exception e) {
                System.out.println("There is an exception!");
                System.out.println(e); // some extra error?  not too sure here
            }

        }

        // Team member D
    }


}


