package cn.tuyucheng.taketoday.servlets.servlets.springboot.embedded;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmbeddedTomcatExample {

   @Bean
   public ConfigurableServletWebServerFactory servletContainer() {
      return new TomcatServletWebServerFactory();
   }
}