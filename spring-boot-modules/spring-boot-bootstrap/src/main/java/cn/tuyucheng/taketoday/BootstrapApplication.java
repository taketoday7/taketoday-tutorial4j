package cn.tuyucheng.taketoday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = "cn.tuyucheng.taketoday")
@EnableJpaRepositories("cn.tuyucheng.taketoday.persistence.repo")
@EntityScan("cn.tuyucheng.taketoday.persistence.model")
public class BootstrapApplication {

   public static void main(String[] args) {
      SpringApplication.run(BootstrapApplication.class, args);
   }
}