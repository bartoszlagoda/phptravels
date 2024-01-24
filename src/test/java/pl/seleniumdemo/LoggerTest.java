package pl.seleniumdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerTest {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger();

        // poziom logowania: trace
        logger.debug("Debug");
        logger.trace("Trace");
        logger.info("Info");
        logger.error("Error");
        logger.warn("Warn");
        logger.fatal("Fatal");

        // poziom logowania: debug
        logger.debug("Debug");
        logger.info("Info");
        logger.error("Error");
        logger.warn("Warn");
        logger.fatal("Fatal");

        // poziom logowania: info
        logger.info("Info");
        logger.error("Error");
        logger.warn("Warn");
        logger.fatal("Fatal");

        // poziom logowania: warn
        logger.error("Error");
        logger.warn("Warn");
        logger.fatal("Fatal");

        // poziom logowania: error
        logger.error("Error");
        logger.fatal("Fatal");

        // poziom logowania: fatal
        logger.fatal("Fatal");
    }
}
