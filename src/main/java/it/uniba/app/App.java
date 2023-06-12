package it.uniba.app;

import it.uniba.app.input.controller.InputController;

/**
 * <noECB>
 *
 * Main class of the application.
 */
public final class App {

    private App() {
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
