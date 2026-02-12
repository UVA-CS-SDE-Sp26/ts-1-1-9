/**
 * Command Line Utility
 */

public class TopSecret {
    public static void main(String[] args) {
        Command command = parseArguments(args);
        switch (command.getType()) {
            case list_files:
                System.out.println("Listing files...");
                break;
            case show_file:
                System.out.println("Showing file " + command.getFileNumber());
                System.out.println("using key: " + command.getKey());
                break;
            case error:
                System.out.println("error" + command.getErrorMessage());
                break;
        }
    }

    public static Command parseArguments(String[] args) {
        if (args.length == 0) {
            return Command.list();
        }
        if (args.length > 2) {
            return Command.error("too many arguments.");
        }
        String fileNumber = args[0];

        if (!fileNumber.matches("\\d{2}")) {
            return Command.error("invalid file number");
        }
        String key = "default";
        if (args.length == 2) {
            key = args[1];
        }
        return Command.show(fileNumber, key);
    }
}
