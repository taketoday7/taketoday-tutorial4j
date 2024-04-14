package cn.tuyucheng.taketoday.springcloudgateway.webfilters;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import redis.embedded.RedisServer;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("webfilters")
@TestConfiguration
class RedisWebFilterFactoriesLiveTest {
   private static final Logger LOGGER = LoggerFactory.getLogger(RedisWebFilterFactoriesLiveTest.class);

   private RedisServer redisServer;

   public RedisWebFilterFactoriesLiveTest() {
   }

   @BeforeEach
   public void postConstruct() {
      this.redisServer = new RedisServer(6379);
      redisServer.start();
   }

   @LocalServerPort
   String port;

   @Autowired
   private TestRestTemplate restTemplate;

   @Autowired
   TestRestTemplate template;

   @RepeatedTest(25)
   void whenCallRedisGetThroughGateway_thenOKStatusOrIsReceived() {
      String url = "http://localhost:" + port + "/redis/get";

      ResponseEntity<String> r = restTemplate.getForEntity(url, String.class);
      // assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

      LOGGER.info("Received: status->{}, remaining->{}",
            r.getStatusCodeValue(), r.getHeaders().get("X-RateLimit-Remaining"));
   }

   @AfterEach
   void preDestroy() {
      redisServer.stop();
   }
}