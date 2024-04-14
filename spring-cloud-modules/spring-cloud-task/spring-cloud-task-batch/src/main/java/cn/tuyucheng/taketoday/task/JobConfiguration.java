package cn.tuyucheng.taketoday.task;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.logging.Logger;

@Configuration
public class JobConfiguration {

   private final static Logger LOGGER = Logger
         .getLogger(JobConfiguration.class.getName());

   @Autowired
   private JobBuilderFactory jobBuilderFactory;

   @Autowired
   private StepBuilderFactory stepBuilderFactory;

   @Bean
   public Step step1() {
      return this.stepBuilderFactory.get("job1step1")
            .tasklet((contribution, chunkContext) -> {
               LOGGER.info("Tasklet has run");
               return RepeatStatus.FINISHED;
            }).build();
   }

   @Bean
   public Step step2() {
      return this.stepBuilderFactory
            .get("job1step2")
            .<String, String>chunk(3)
            .reader(
                  new ListItemReader<>(Arrays.asList("7",
                        "2", "3", "10", "5", "6")))
            .processor(
                  (ItemProcessor<String, String>) item -> {
                     LOGGER.info("Processing of chunks");
                     return String.valueOf(Integer
                           .parseInt(item) * -1);
                  })
            .writer(items -> {
               for (String item : items) {
                  LOGGER.info(">> " + item);
               }
            }).build();
   }

   @Bean
   public Job job1() {
      return this.jobBuilderFactory.get("job1")
            .start(step1())
            .next(step2())
            .build();
   }

   @Bean
   public Job job2() {
      return jobBuilderFactory.get("job2")
            .start(stepBuilderFactory.get("job2step1")
                  .tasklet((contribution, chunkContext) -> {
                     LOGGER.info("This job is from Tuyucheng");
                     return RepeatStatus.FINISHED;
                  })
                  .build())
            .build();
   }
}