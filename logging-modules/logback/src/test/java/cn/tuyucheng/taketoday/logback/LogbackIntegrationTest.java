package cn.tuyucheng.taketoday.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

class LogbackIntegrationTest {

   @Test
   void givenLogHierarchy_MessagesFiltered() {
      ch.qos.logback.classic.Logger parentLogger =
            (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("cn.tuyucheng.taketoday.logback");

      parentLogger.setLevel(Level.INFO);

      Logger childlogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("cn.tuyucheng.taketoday.logback.tests");

      parentLogger.warn("This message is logged because WARN > INFO.");

      // This request is disabled, because DEBUG < INFO.
      parentLogger.debug("This message is not logged because DEBUG < INFO.");

      childlogger.info("INFO == INFO");

      childlogger.debug("DEBUG < INFO");
   }

   @Test
   void givenRootLevel_MessagesFiltered() {
      ch.qos.logback.classic.Logger logger =
            (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("cn.tuyucheng.taketoday.logback");

      logger.debug("Hi there!");

      Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

      logger.debug("This message is logged because DEBUG == DEBUG.");

      rootLogger.setLevel(Level.ERROR);
      logger.warn("This message is not logged because WARN < ERROR.");

      logger.error("This is logged.");
   }

   @Test
   void givenParameters_ValuesLogged() {
      Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(LogbackIntegrationTest.class);

      String message = "This is a String";
      Integer zero = 0;

      try {
         logger.debug("Logging message: {}", message);
         logger.debug("Going to divide {} by {}", 42, zero);
         int result = 42 / zero;
      } catch (Exception e) {
         logger.error("Error dividing {} by {} ", 42, zero, e);
      }
   }

   @Test
   void givenConfig_MessageFiltered() {
      Logger foobar = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("cn.tuyucheng.taketoday.foobar");
      Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("cn.tuyucheng.taketoday.logback");
      Logger testslogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("cn.tuyucheng.taketoday.logback.tests");

      foobar.debug("This is logged from foobar");
      logger.debug("This is not logged from logger");
      logger.info("This is logged from logger");
      testslogger.info("This is not logged from tests");
      testslogger.warn("This is logged from tests");
   }
}