package it.uniba.app;

/**
 * Classe main dell'applicazione.
 */
public abstract class App {

    /**
     * Entrypoint dell'applicazione.
     *
     * @param args comandi da linea di comand
     */
    public static void main(final String[] args) {
        Partita partita = new Partita();
        partita.execute();
    }
}
