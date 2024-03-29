package it.uniba.app.type;

/**
 * <Entity>
 *
 * The Difficulty class is an enumeration that represents different difficulty
 * levels in a game. It includes information about the number of tries allowed
 * for each difficulty level.
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
     * Default tries.
     */
    private final int tries;

    /**
     * Difficulty of the current match.
     */
    private static Difficulty gameDifficulty = Difficulty.MEDIUM; // default difficulty

    /**
     * Max tries of the current match.
     */
    private static int maxTries = gameDifficulty.getTries();

    /**
     * Tries of the current match.
     */
    private static int failedTries = gameDifficulty.getTries();

    /**
     * Tries of the current match.
     */
    private static int currentTries = 0;

    /**
     * Returns the current number of tries.
     *
     * @return The current number of tries.
     */
    public static int getCurrentTries() {
        return currentTries;
    }

    /**
     * Sets the current number of tries.
     *
     * @param valCurrentTries The new value for the current number of tries.
     */
    public static void setCurrentTries(final int valCurrentTries) {
        if (valCurrentTries < 0) {
            Difficulty.currentTries = 0;
        } else {
            Difficulty.currentTries = valCurrentTries;
        }
    }

    /**
     * Constructor of the difficulty class.
     */
    Difficulty(final int valTries) {
        this.tries = valTries;
    }

    /**
     * Returns number of tries.
     */
    public static int getMaxTries() {
        return maxTries;
    }

    /**
     * Set the current number of tries.
     *
     * @param valTries number of tries
     * @throws IllegalArgumentException if the number is less than 0
     */
    public static void setMaxTries(final int valTries) throws IllegalArgumentException {
        if (valTries > 0) {
            maxTries = valTries;
            failedTries = maxTries;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Change the current difficulty and set the standard value of tries.
     *
     * @param valDifficulty difficulty of the game
     */
    public static void setDifficulty(final Difficulty valDifficulty) {
        if (valDifficulty == null) {
            throw new IllegalArgumentException();
        }
        gameDifficulty = valDifficulty;
        setMaxTries(gameDifficulty.getTries());
    }

    /**
     * Returns the current game difficulty.
     *
     * @return the current game difficulty
     */
    public static Difficulty getDifficulty() {
        return gameDifficulty;
    }

    /**
     * Returns the current number of tries allowed for the game difficulty.
     *
     * @return the current number of tries
     */
    public static int getFailedTries() {
        return failedTries;
    }

    /**
     * Sets the current number of tries allowed for the game difficulty.
     *
     * @param valFailedTries the new value for the current number of tries
     */
    public static void setFailedTries(final int valFailedTries) {
        if (valFailedTries >= 0 && valFailedTries <= maxTries) {
            Difficulty.failedTries = valFailedTries;
        } else if (valFailedTries < 0) {
            Difficulty.failedTries = 0;
        } else {
            Difficulty.failedTries = maxTries;
        }
    }

    /**
     * Returns the number of max tries.
     *
     * @return the number of max tries
     */
    public int getTries() {
        return this.tries;
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
