package cn.tuyucheng.taketoday.config;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"cn.tuyucheng.taketoday.controller.parameterized"})
public class WebConfig {

   @Autowired
   private ServletContext ctx;
}