package it.uniba.app;

import it.uniba.app.input.controller.InputController;

/**
 * Main class of the application.
 */
public final class App {

    /**
     * <noECB>
     * 
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
        InputController controller = InputController.CONTROLLER;
        boolean helpFlag = controller.controlFlag(args);
        controller.start(helpFlag);
    }
}
