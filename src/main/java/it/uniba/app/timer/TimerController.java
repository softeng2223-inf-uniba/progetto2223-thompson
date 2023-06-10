package it.uniba.app.timer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Utility class for managing game timers.
 */
public class TimerController {
    private static final long DEFAULT_TIME = 10;
    private static final int SECONDS_IN_MINUTE = 60;
    private static long maxTime = TimeUnit.MILLISECONDS.convert(DEFAULT_TIME, TimeUnit.MINUTES);
    private static Timer timer;
    private static boolean isRunning;
    private static long startTime;

    private static void setStartTime(final long valStartTime) {
        TimerController.startTime = valStartTime;
    }

    /**
     * Creates a new timer instance.
     */
    private static void createTimer() {
        timer = new Timer();
    }

    /**
     * Checks if the timer is currently running.
     *
     * @return true if the timer is running, false otherwise
     */
    public static boolean isRunning() {
        return isRunning;
    }

    /**
     * Sets the running state of the timer.
     *
     * @param valIsRunning true to set the timer as running, false otherwise
     */
    public static void setRunning(final boolean valIsRunning) {
        isRunning = valIsRunning;
    }

    /**
     * Starts the game timer.
     */
    public void startGame() {
        createTimer();
        setStartTime(System.currentTimeMillis());

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                stopTimer();
            }
        };
        timer.schedule(task, maxTime);
        System.out.println("Il timer di " + getMaxTime() + " minuti.");
        setRunning(true);
    }

    /**
     * Prints the current time and remaining time based on the maximum time.
     */
    public static void printCurrentAndRemainingTime() {
        printCurrentTime();
        long currentTimeMillis = getCurrentTimeMillis();
        long currentTimeSeconds = TimeUnit.SECONDS.convert(currentTimeMillis, TimeUnit.MILLISECONDS);
        long maxTimeSeconds = TimeUnit.SECONDS.convert(maxTime, TimeUnit.MILLISECONDS);

        long minutesRemaining = (maxTimeSeconds - currentTimeSeconds) / SECONDS_IN_MINUTE;
        long secondsRemaining = (maxTimeSeconds - currentTimeSeconds) % SECONDS_IN_MINUTE;

        if (minutesRemaining > 1) {
            System.out.println("Mancano ancora " + minutesRemaining + " minuti e " + secondsRemaining + " secondi.");
        } else if (minutesRemaining == 1) {
            System.out.println("Manca ancora " + minutesRemaining + " minuto e " + secondsRemaining + " secondi.");
        } else if (minutesRemaining == 0) {
            System.out.println("Mancano ancora " + secondsRemaining + " secondi.");
        }
    }

    /**
     * Prints the current time.
     */
    public static void printCurrentTime() {
        long currentTimeMillis = getCurrentTimeMillis();
        long currentTimeSeconds = TimeUnit.SECONDS.convert(currentTimeMillis, TimeUnit.MILLISECONDS);

        long minutesPassed = currentTimeSeconds / SECONDS_IN_MINUTE;
        long secondsPassed = currentTimeSeconds % SECONDS_IN_MINUTE;
        if (minutesPassed == 0) {
            System.out.println("Sono passati " + secondsPassed + " secondi.");
        } else if (minutesPassed == 1) {
            System.out.println("E' passato " + minutesPassed + " minuto e " + secondsPassed + " secondi.");
        } else if (minutesPassed > 1) {
            System.out.println("Sono passati " + minutesPassed + " minuti e " + secondsPassed + " secondi.");
        }
    }

    /**
     * Stops the game timer if it is running.
     */
    public static void stopTimer() {
        if (isRunning) {
            System.out.println("Il timer è scaduto. La partita è finita!");
            setRunning(false);
        }
        timer.cancel();
    }

    /**
     * Returns the maximum time in minutes for the game timer.
     *
     * @return the maximum time in minutes
     */
    public static long getMaxTime() {
        return TimeUnit.MINUTES.convert(maxTime, TimeUnit.MILLISECONDS);
    }

    /**
     * Sets the maximum time in minutes for the game timer.
     *
     * @param minutes the maximum time in minutes
     */
    public static void setMaxTime(final int minutes) {
        TimerController.maxTime = TimeUnit.MILLISECONDS.convert(minutes, TimeUnit.MINUTES);
    }

    /**
     * Returns the current time in milliseconds elapsed since the start time.
     *
     * @return the current time in milliseconds
     */
    private static long getCurrentTimeMillis() {
        return System.currentTimeMillis() - startTime;
    }
}
