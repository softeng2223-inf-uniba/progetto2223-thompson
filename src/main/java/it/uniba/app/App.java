package it.uniba.app;

/**
 * Main class of the application.
 */
public abstract class App {

    /**
     * Entrypoint of the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        Partita partita = new Partita();
        partita.execute();
    }
}
