package it.uniba.app.type;

/**
 * Class containing difficulty and number of attempts.
 */
public enum Difficulty {
    /**
     * Easy difficulty with 50 tries.
     */
    EASY(50),
    /**
     * Medium difficulty with 30 tries.
     */
    MEDIUM(30),
    /**
     * Hard difficulty with 10 tries.
     */
    HARD(10);

    /**
     * Contains number of tries.
     */
    private final int tries;

    /**
     * Constructor of the difficulty class.
     */
    Difficulty(final int valTries) {
        this.tries = valTries;
    }

    /**
     * Returns number of tries.
     */
    public int getTries() {
        return tries;
    }

    /**
     * This method return a string that contains the level difficulty in italian.
     */
    @Override
    public String toString() {
        if (null != this) {
            switch (this) {
                case HARD -> {
                    return "difficile";
                }
                case MEDIUM -> {
                    return "medio";
                }
                case EASY -> {
                    return "facile";
                }
                default -> {
                }
            }
        }
        return "";
    }
}
