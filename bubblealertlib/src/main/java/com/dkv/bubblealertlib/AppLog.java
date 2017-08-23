package com.dkv.bubblealertlib;

import android.util.Log;


/**
 * Utility class for log.
 * <p>
 * For utility class for logging.
 *
 * @author Zco
 */
public class AppLog {
    // Application Tag
    private static final String APP_TAG = "BUBBLE_ALERT";

    /**
     * Logs application debug messages.
     *
     * @param message The message to write to console.
     */
    public static int logString(String message) {
        return logString(APP_TAG, message);
    }

    /**
     * Logs application debug messages.
     *
     * @param message The error message to write to console.
     */
    public static int logErrorString(String message) {
        return logErrorString(APP_TAG, message);
    }

    /**
     * Logs application debug messages.
     *
     * @param tag     The log tag.
     * @param message The message to write to console.
     */
    public static int logString(String tag, String message) {
        int result = 0;

        // If log is enabled, the print the log

        result = Log.i(tag, " " + message);


        return result;
    }

    /**
     * Logs application debug messages.
     *
     * @param tag     The log tag.
     * @param message The error message to write to console.
     */
    public static int logErrorString(String tag, String message) {
        int result = 0;

        // If log is enabled, the print the log

        result = Log.e(tag, " " + message);


        return result;
    }


    /**
     * Logs application debug messages.
     *
     * @param e Exception thrown.
     */
    public static void logException(String tag, Exception e) {


        // If log is enabled, the print the log

        e.printStackTrace();


    }
}
