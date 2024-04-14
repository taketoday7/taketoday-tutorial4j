package cn.tuyucheng.taketoday.kong;

import cn.tuyucheng.taketoday.kong.domain.APIObject;
import cn.tuyucheng.taketoday.kong.domain.TargetObject;
import cn.tuyucheng.taketoday.kong.domain.UpstreamObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

/**
 * @author tuyucheng
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = DEFINED_PORT, classes = StockApplication.class, properties = "server.servlet.contextPath=/springbootapp")
class KongLoadBalanceLiveTest {

   @BeforeEach
   public void init() {
      System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
   }

   @Autowired
   TestRestTemplate restTemplate;

   @Test
   void givenKongAdminAPI_whenAddAPI_thenAPIAccessibleViaKong() throws Exception {
      UpstreamObject upstream = new UpstreamObject("stock.api.service");
      ResponseEntity<String> addUpstreamResp = restTemplate.postForEntity("http://localhost:8001/upstreams", new HttpEntity<>(upstream), String.class);
      assertTrue(HttpStatus.CREATED == addUpstreamResp.getStatusCode() || HttpStatus.CONFLICT == addUpstreamResp.getStatusCode());

      TargetObject testTarget = new TargetObject("localhost:8080", 10);
      ResponseEntity<String> addTargetResp = restTemplate.postForEntity("http://localhost:8001/upstreams/stock.api.service/targets", new HttpEntity<>(testTarget), String.class);
      assertTrue(HttpStatus.CREATED == addTargetResp.getStatusCode() || HttpStatus.CONFLICT == addTargetResp.getStatusCode());

      TargetObject releaseTarget = new TargetObject("localhost:9090", 40);
      addTargetResp = restTemplate.postForEntity("http://localhost:8001/upstreams/stock.api.service/targets", new HttpEntity<>(releaseTarget), String.class);
      assertTrue(HttpStatus.CREATED == addTargetResp.getStatusCode() || HttpStatus.CONFLICT == addTargetResp.getStatusCode());

      APIObject stockAPI = new APIObject("balanced-stock-api", "balanced.stock.api", "http://stock.api.service", "/");
      HttpEntity<APIObject> apiEntity = new HttpEntity<>(stockAPI);
      ResponseEntity<String> addAPIResp = restTemplate.postForEntity("http://localhost:8001", apiEntity, String.class);
      assertTrue(HttpStatus.CREATED == addAPIResp.getStatusCode() || HttpStatus.CONFLICT == addAPIResp.getStatusCode());

      HttpHeaders headers = new HttpHeaders();
      headers.set("Host", "balanced.stock.api");
      for (int i = 0; i < 1000; i++) {
         RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI("http://localhost:8000/springbootapp/stock/btc"));
         ResponseEntity<String> stockPriceResp = restTemplate.exchange(requestEntity, String.class);
         assertEquals("10000", stockPriceResp.getBody());
      }

      int releaseCount = restTemplate.getForObject("http://localhost:9090/springbootapp/stock/reqcount", Integer.class);
      int testCount = restTemplate.getForObject("http://localhost:8080/springbootapp/stock/reqcount", Integer.class);

      assertEquals(4, Math.round(releaseCount * 1.0 / testCount));
   }
}