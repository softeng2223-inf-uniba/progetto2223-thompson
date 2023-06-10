package it.uniba.app.timer;

/**
 * <Boundary>
 * 
 * Class to print timer information.
 */
public abstract class TimerBoundary {
    /**
     * Returns the maximum time in minutes for the game timer.
     *
     * @return the maximum time in minutes
     */
    protected abstract long getMaxTime();

    /**
     * Returns the current time in seconds.
     *
     * @return the current time in seconds
     */
    protected abstract long getCurrentTimeSecond();

    /**
     * Returns the maximum time in seconds.
     *
     * @return the maximum time in seconds
     */
    protected abstract long getMaxTimeSecond();

    /**
     * Calculates the number of minutes passed based on the given current time in
     * seconds.
     *
     * @param currentTimeSeconds the current time in seconds
     * @return the number of minutes passed
     */
    protected abstract long getMinutePassed(long currentTimeSeconds);

    /**
     * Calculates the number of seconds passed based on the given current time in
     * seconds.
     *
     * @param currentTimeSeconds the current time in seconds
     * @return the number of seconds passed
     */
    protected abstract long getSecondPassed(long currentTimeSeconds);

    /**
     * Prints a message indicating the start of the timer.
     */
    protected final void startTimerMessage() {
        System.out.println("Il timer di " + getMaxTime() + " minuti.");
    }

    /**
     * Prints a message indicating that the timer has expired and the game is over.
     */
    protected final void endTimerMessage() {
        System.out.println("Il timer Ã¨ scaduto. La partita Ã¨ finita!");
    }

    /**
     * Prints the current time.
     */
    public void printCurrentTime() {
        long minutesPassed = getMinutePassed(getCurrentTimeSecond());
        long secondsPassed = getSecondPassed(getCurrentTimeSecond());
        if (minutesPassed == 0) {
            System.out.println("Sono passati " + secondsPassed + " secondi.");
        } else if (minutesPassed == 1) {
            System.out.println("E' passato " + minutesPassed + " minuto e " + secondsPassed + " secondi.");
        } else if (minutesPassed > 1) {
            System.out.println("Sono passati " + minutesPassed + " minuti e " + secondsPassed + " secondi.");
        }
    }

    /**
     * Prints the current time and remaining time based on the maximum time.
     */
    public void printCurrentAndRemainingTime() {
        printCurrentTime();
        long minutesRemaining = getMinutePassed((getMaxTimeSecond() - getCurrentTimeSecond()));
        long secondsRemaining = getSecondPassed((getMaxTimeSecond() - getCurrentTimeSecond()));

        if (minutesRemaining > 1) {
            System.out.println("Mancano ancora " + minutesRemaining + " minuti e " + secondsRemaining + " secondi.");
        } else if (minutesRemaining == 1) {
            System.out.println("Manca ancora " + minutesRemaining + " minuto e " + secondsRemaining + " secondi.");
        } else if (minutesRemaining == 0) {
            System.out.println("Mancano ancora " + secondsRemaining + " secondi.");
        }
    }
}
