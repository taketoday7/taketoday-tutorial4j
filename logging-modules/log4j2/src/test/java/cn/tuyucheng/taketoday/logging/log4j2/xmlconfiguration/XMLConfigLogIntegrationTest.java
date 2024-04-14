/**
 * This class loads the logging configuration from the xml defined in
 * src/main/resources and uses the same configuration  generated through
 * programmatic configuration as defined in simple-configuration example.
 **/

package cn.tuyucheng.taketoday.logging.log4j2.xmlconfiguration;

import cn.tuyucheng.taketoday.logging.log4j2.Log4j2BaseIntegrationTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.plugins.util.PluginManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class XMLConfigLogIntegrationTest extends Log4j2BaseIntegrationTest {

   @BeforeAll
   static void setUp() {
      PluginManager.addPackage("cn.tuyucheng.taketoday.logging.log4j2.xmlconfiguration");
   }

   @Test
   void givenXMLConfigurationPlugin_whenUsingFlowMarkers_ThenLogsCorrectly() {
      Logger logger = LogManager.getLogger(this.getClass());
      Marker markerContent = MarkerManager.getMarker("FLOW");
      logger.debug(markerContent, "Debug log message");
      logger.info(markerContent, "Info log message");
      logger.error(markerContent, "Error log message");
   }

   @Test
   void givenXMLConfigurationPlugin_whenSimpleLog_ThenLogsCorrectly() {
      Logger logger = LogManager.getLogger(this.getClass());
      LoggerContext ctx = (LoggerContext) LogManager.getContext();
      logger.debug("Debug log message");
      logger.info("Info log message");
      logger.error("Error log message");
   }
}