package cn.tuyucheng.taketoday.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

import java.util.TimeZone;

@SpringBootApplication
public class Application {

   @PostConstruct
   void started() {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
   }

   public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
   }
}