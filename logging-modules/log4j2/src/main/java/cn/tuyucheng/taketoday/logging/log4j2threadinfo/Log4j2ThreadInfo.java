package cn.tuyucheng.taketoday.logging.log4j2threadinfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.IntStream;

public class Log4j2ThreadInfo {
   private static final Logger logger = LogManager.getLogger(Log4j2ThreadInfo.class);

   public static void main(String[] args) {
      IntStream.range(0, 5).forEach(i -> {
         Runnable runnable = () -> logger.info("Logging info");
         Thread thread = new Thread(runnable);
         thread.start();
      });
   }
}
