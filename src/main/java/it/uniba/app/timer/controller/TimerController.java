package it.uniba.app.timer.controller;

import it.uniba.app.input.controller.InputController;
import it.uniba.app.timer.TimerBoundary;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * <Control>
 *
 * The TimerController class is a class for managing game timers. It
 * provides functionality to start and stop a timer, set the maximum time for
 * the timer, and retrieve information about the timer's current and maximum
 * time.
 */
public final class TimerController extends TimerBoundary {
    private static final TimerController CONTROLLER = new TimerController();
    private static final long DEFAULT_TIME = 10;
    private static final int SECONDS_IN_MINUTE = 60;
    private long maxTime = TimeUnit.MILLISECONDS.convert(DEFAULT_TIME, TimeUnit.MINUTES);
    private Timer timer;
    private boolean isRunning;
    private long startTime;

    /**
     * Returns the instance of the TimerController class.
     */
    public static TimerController getInstance() {
        return CONTROLLER;
    }

    private TimerController() {
    }

    private void setStartTime(final long valStartTime) {
        this.startTime = valStartTime;
    }

    /**
     * Checks if the timer is currently running.
     *
     * @return true if the timer is running, false otherwise
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Sets the running state of the timer.
     *
     * @param valIsRunning true to set the timer as running, false otherwise
     */
    public void setRunning(final boolean valIsRunning) {
        this.isRunning = valIsRunning;
    }

    /**
     * Starts the game timer.
     */
    public void startGame() {
        this.timer = new Timer();
        setStartTime(System.currentTimeMillis());

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                stopTimer();
            }
        };
        timer.schedule(task, maxTime);
        startTimerMessage();
        setRunning(true);
    }

    /**
     * Stops the game timer if it is running.
     */
    public void stopTimer() {
        InputController controller = InputController.getInstance();
        if (isRunning) {
            endTimerMessage();
            controller.printGrid();
            setRunning(false);
        }
        timer.cancel();
    }

    /**
     * Sets the maximum time in minutes for the game timer.
     *
     * @param minutes the maximum time in minutes
     */
    public void setMaxTime(final int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("The maximum time cannot be negative.");
        }
        this.maxTime = TimeUnit.MILLISECONDS.convert(minutes, TimeUnit.MINUTES);
    }

    /**
     * Returns the current time in milliseconds elapsed since the start time.
     *
     * @return the current time in milliseconds
     */
    private long getCurrentTimeMillis() {
        return System.currentTimeMillis() - startTime;
    }

    private long milliToSecond(final long milliseconds) {
        return TimeUnit.SECONDS.convert(milliseconds, TimeUnit.MILLISECONDS);
    }

    /**
     * Returns the maximum time in minutes for the game timer.
     *
     * @return the maximum time in minutes
     */
    @Override
    protected long getMaxTime() {
        return TimeUnit.MINUTES.convert(maxTime, TimeUnit.MILLISECONDS);
    }

    /**
     * Returns the current time in seconds.
     *
     * @return the current time in seconds
     */
    @Override
    protected long getCurrentTimeSecond() {
        return milliToSecond(getCurrentTimeMillis());
    }

    /**
     * Returns the maximum time in seconds.
     *
     * @return the maximum time in seconds
     */
    @Override
    protected long getMaxTimeSecond() {
        return milliToSecond(maxTime);
    }

    /**
     * Calculates the number of minutes passed based on the given current time in
     * seconds.
     *
     * @param currentTimeSeconds the current time in seconds
     * @return the number of minutes passed
     */
    @Override
    protected long getMinutePassed(final long currentTimeSeconds) {
        return currentTimeSeconds / SECONDS_IN_MINUTE;
    }

    /**
     * Calculates the number of seconds passed based on the given current time in
     * seconds.
     *
     * @param currentTimeSeconds the current time in seconds
     * @return the number of seconds passed
     */
    @Override
    protected long getSecondPassed(final long currentTimeSeconds) {
        return currentTimeSeconds % SECONDS_IN_MINUTE;
    }

}
