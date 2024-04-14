package cn.tuyucheng.taketoday.springbootlogging.disablingconsole.log4j2.xml.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class DisabledConsoleRestController {

   private static final Logger LOG = LoggerFactory.getLogger(DisabledConsoleRestController.class);

   @GetMapping("/disabled-console-log4j2-xml")
   public String logTime() {
      LOG.info("Current time: {}", LocalTime.now());
      return "finished!";
   }
}