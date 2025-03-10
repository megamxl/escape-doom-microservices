package at.escapedoom.data.utils;

import org.slf4j.Logger;

import java.util.UUID;

public class LoggerUtils {

    public static void logCreation(Logger log, LogType logType, UUID uuid, Class<?> clazz) {
        // TODO: Get user
        log.info("{} {}: {}", logType.name, clazz.getSimpleName(), uuid.toString());
        // Results in 'Created Riddle: 05c48cb1-a3aa-4673-8d24-20d718977600' for a riddle creation
    }

    public enum LogType {
        CREATION("Created"), UPDATE("Updated"), DELETION("Deleted");

        private final String name;

        LogType(String name) {
            this.name = name;
        }
    }
}
