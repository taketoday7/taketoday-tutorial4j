package cn.tuyucheng.taketoday.exitcode.exception;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExitCodeExceptionMapperDemoApplication {

   public static void main(String[] args) {
      SpringApplication.run(ExitCodeExceptionMapperDemoApplication.class, args);
   }

   @Bean
   CommandLineRunner createException() {
      return _ -> Integer.parseInt("test");
   }

   @Bean
   ExitCodeExceptionMapper exitCodeToExceptionMapper() {
      return exception -> {
         // set exit code based on the exception type
         if (exception.getCause() instanceof NumberFormatException) {
            return 80;
         }
         return 1;
      };
   }
}