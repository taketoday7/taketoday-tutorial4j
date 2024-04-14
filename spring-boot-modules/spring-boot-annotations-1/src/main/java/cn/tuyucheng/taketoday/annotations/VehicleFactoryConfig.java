package cn.tuyucheng.taketoday.annotations;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages = "cn.tuyucheng.taketoday.annotations")
@ComponentScan(basePackageClasses = VehicleFactoryConfig.class)
@ImportResource("classpath:/annotations.xml")
@PropertySource("classpath:/annotations.properties")
@Lazy
@EnableAutoConfiguration
@EnableAsync
@EnableScheduling
public class VehicleFactoryConfig {

   @Bean
   @Lazy(false)
   public Engine engine() {
      return new Engine();
   }
}