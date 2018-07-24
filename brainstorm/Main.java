package brainstorm;

public abstract class Main {
    /**
     * The starting point of the program. This will create all of the
     * necessary controllers, models, and views so that the program
     * can run.
     * 
     * @param args Arguments passed to the program from the system.
     */
    public static void main(final String[] args) {
        ApplicationController.getInstance().begin();
    }
}
