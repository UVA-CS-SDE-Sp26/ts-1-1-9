public class TopSecret {
    public static void main(String[] args) {
        FileHandler handler = new FileHandler();
        ProgramController controller = new ProgramController(handler);

        controller.run(args);
    }
}