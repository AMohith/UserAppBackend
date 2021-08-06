package com.company.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MongoLogger.loggerSuppression method Suppresses the logs that are created by mongodb.driver
 * till the level is warning.
 */
public class MongoLogger {
    public static void loggerSuppression() {
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
    }
}

