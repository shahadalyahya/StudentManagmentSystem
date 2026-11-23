 
package com.studentmanagmentsystem.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppLogger {

    private AppLogger() {
    }

    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }
}
