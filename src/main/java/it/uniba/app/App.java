package it.uniba.app;

/**
 * Main class of the application.
 */
public final class App {

    /**
     * Get a greeting sentence.
     *
     * @return the "Hello World!" string.
     */
    public String getGreeting() {
        return "Hello World!!!";
    }

    /**
     * Entrypoint of the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        InputBoundary partita = new InputBoundary();
        boolean helpFlag = partita.controlFlag(args);
        partita.execute(helpFlag);
    }
}
