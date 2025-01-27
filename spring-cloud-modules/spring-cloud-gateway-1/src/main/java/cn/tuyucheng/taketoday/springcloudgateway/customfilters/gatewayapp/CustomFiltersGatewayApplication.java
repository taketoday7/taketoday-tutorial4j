package cn.tuyucheng.taketoday.springcloudgateway.customfilters.gatewayapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:customfilters-global-application.properties")
public class CustomFiltersGatewayApplication {

   public static void main(String[] args) {
      SpringApplication.run(CustomFiltersGatewayApplication.class, args);
   }
}