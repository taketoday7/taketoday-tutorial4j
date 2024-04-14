package cn.tuyucheng.taketoday.spring.data.reactive.redis.template;


import cn.tuyucheng.taketoday.spring.data.reactive.redis.SpringRedisReactiveApplication;
import cn.tuyucheng.taketoday.spring.data.reactive.redis.model.Employee;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import redis.embedded.RedisServerBuilder;

import java.io.IOException;
import java.time.Duration;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringRedisReactiveApplication.class)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RedisTemplateValueOpsManualTest {

   private static redis.embedded.RedisServer redisServer;

   @Autowired
   private ReactiveRedisTemplate<String, Employee> redisTemplate;

   private ReactiveValueOperations<String, Employee> reactiveValueOps;

   @BeforeAll
   static void startRedisServer() throws IOException {
      redisServer = new RedisServerBuilder().port(6379).setting("maxmemory 256M").build();
      redisServer.start();
   }

   @AfterAll
   static void stopRedisServer() throws IOException {
      redisServer.stop();
   }

   @BeforeEach
   void setup() {
      reactiveValueOps = redisTemplate.opsForValue();
   }

   @Test
   void givenEmployee_whenSet_thenSet() {

      Mono<Boolean> result = reactiveValueOps.set("123", new Employee("123", "Bill", "Accounts"));

      StepVerifier.create(result)
            .expectNext(true)
            .verifyComplete();
   }

   @Test
   void givenEmployeeId_whenGet_thenReturnsEmployee() {

      Mono<Employee> fetchedEmployee = reactiveValueOps.get("123");

      StepVerifier.create(fetchedEmployee)
            .expectNext(new Employee("123", "Bill", "Accounts"))
            .verifyComplete();
   }

   @Test
   void givenEmployee_whenSetWithExpiry_thenSetsWithExpiryTime() throws InterruptedException {

      Mono<Boolean> result = reactiveValueOps.set("129", new Employee("129", "John", "Programming"), Duration.ofSeconds(1));

      Mono<Employee> fetchedEmployee = reactiveValueOps.get("129");

      StepVerifier.create(result)
            .expectNext(true)
            .verifyComplete();

      Thread.sleep(2000L);

      StepVerifier.create(fetchedEmployee)
            .expectNextCount(0L)
            .verifyComplete();
   }

}
