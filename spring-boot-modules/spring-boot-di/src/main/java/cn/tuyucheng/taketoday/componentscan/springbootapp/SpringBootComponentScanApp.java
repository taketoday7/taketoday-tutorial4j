package cn.tuyucheng.taketoday.componentscan.springbootapp;

import cn.tuyucheng.taketoday.componentscan.ExampleBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// @ComponentScan(basePackages = "cn.tuyucheng.taketoday.componentscan.springbootapp.animals")
// @ComponentScan ( excludeFilters = @ComponentScan.Filter(type=FilterType.REGEX,pattern="cn\\.tuyucheng\\.taketoday\\.componentscan\\.springbootapp\\.flowers\\..*"))
// @ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Rose.class))
public class SpringBootComponentScanApp {
   private static ApplicationContext applicationContext;

   @Bean
   public ExampleBean exampleBean() {
      return new ExampleBean();
   }

   public static void main(String[] args) {
      applicationContext = SpringApplication.run(SpringBootComponentScanApp.class, args);
      checkBeansPresence("cat", "dog", "rose", "exampleBean", "springBootApp");

   }

   private static void checkBeansPresence(String... beans) {
      for (String beanName : beans) {
         System.out.println(STR."Is \{beanName} in ApplicationContext: \{applicationContext.containsBean(beanName)}");
      }
   }
}