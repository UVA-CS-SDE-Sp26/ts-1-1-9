public class TopSecret {
    private final ProgramController controller;

    public TopSecret(ProgramController controller) {
        this.controller = controller;
    }
    public void start(String[] args) {
        controller.run(args);
    }
    public static void main(String[] args) {
        FileHandler handler = new FileHandler();
        ProgramController controller = new ProgramController(handler);

        TopSecret app = new TopSecret(controller);
        app.start(args);
    }
}