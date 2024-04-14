package cn.tuyucheng.taketoday.reactive.cors.webfilter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.List;

@Configuration
public class CorsWebFilterConfig {

   @Bean
   CorsWebFilter corsWebFilter() {
      CorsConfiguration corsConfig = new CorsConfiguration();
      corsConfig.setAllowedOrigins(List.of("http://allowed-origin.com"));
      corsConfig.setMaxAge(8000L);
      corsConfig.addAllowedMethod("PUT");
      corsConfig.addAllowedHeader("Tuyucheng-Allowed");

      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
      source.registerCorsConfiguration("/**", corsConfig);

      return new CorsWebFilter(source);
   }
}