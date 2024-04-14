package cn.tuyucheng.taketoday.errorhandling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("errorhandling")
@SpringBootApplication(scanBasePackages = "cn.tuyucheng.taketoday.errorhandling")
public class ErrorHandlingApplication {

   public static void main(String[] args) {
      System.setProperty("spring.profiles.active", "errorhandling");
      SpringApplication.run(ErrorHandlingApplication.class, args);
   }
}