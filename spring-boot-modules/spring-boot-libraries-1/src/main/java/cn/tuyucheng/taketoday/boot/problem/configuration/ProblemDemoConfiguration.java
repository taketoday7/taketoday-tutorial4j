package cn.tuyucheng.taketoday.boot.problem.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.jackson.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

@Configuration
public class ProblemDemoConfiguration {

   @Bean
   public ObjectMapper objectMapper() {
      // In this example, stack traces support is enabled by default.
      // If you want to disable stack traces just use new ProblemModule() instead of new ProblemModule().withStackTraces()
      return new ObjectMapper().registerModules(new ProblemModule().withStackTraces(), new ConstraintViolationProblemModule());
   }
}