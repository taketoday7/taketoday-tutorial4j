package cn.tuyucheng.taketoday.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
public class AppConfig {

   @Bean
   public FilterRegistrationBean<DelegatingFilterProxy> loggingFilterDelegatingProxy() {
      FilterRegistrationBean<DelegatingFilterProxy> registrationBean = new FilterRegistrationBean<>();
      registrationBean.setFilter(new DelegatingFilterProxy("loggingFilterDelegateProxy"));
      registrationBean.addUrlPatterns("/*");
      return registrationBean;
   }

   @Bean
   @Autowired
   public FilterRegistrationBean<LoggingFilterRegistrationBean> loggingFilterRegistration(LoggingService loggingService) {
      FilterRegistrationBean<LoggingFilterRegistrationBean> registrationBean = new FilterRegistrationBean<>();
      registrationBean.setFilter(new LoggingFilterRegistrationBean(loggingService));
      registrationBean.addUrlPatterns("/*");
      return registrationBean;
   }
}