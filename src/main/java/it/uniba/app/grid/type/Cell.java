package it.uniba.app.grid.type;


/**
 *
 * @author Utente
 */
public final class Cell {

    private State state;

    public Cell() {
        this.state = State.VOID;
    }

    public State getState() {
        return state;
    }

    private void setState(final State valState) {
        this.state = valState;
    }

    public boolean isEmpty() {
        return getState() == State.VOID;
    }
}
