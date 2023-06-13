package it.uniba.app.grid.type;

/**
 * <noECB>
 *
 * The ResultRemove class represents the result of a ship removal operation.
 * It encapsulates the outcome of the removal operation and provides access to
 * the result and an associated message.
 */
public class ResultRemove {
    private boolean result;
    private String message;

    /**
     * Constructs a new instance of ResultRemove.
     *
     * @param valResult  the result of the removal operation
     * @param valMessage the message associated with the removal result
     */
    public ResultRemove(final boolean valResult, final String valMessage) {
        this.result = valResult;
        this.message = valMessage;
    }

    /**
     * Returns the result of the removal operation.
     *
     * @return the result of the removal operation
     */
    public final boolean getResult() {
        return result;
    }

    /**
     * Returns the message associated with the removal result.
     *
     * @return the message associated with the removal result
     */
    public final String getMessage() {
        return message;
    }
}
