package cn.tuyucheng.taketoday.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan({"cn.tuyucheng.taketoday.web.controller"})
public class ResourceServerWebConfig implements WebMvcConfigurer {
}