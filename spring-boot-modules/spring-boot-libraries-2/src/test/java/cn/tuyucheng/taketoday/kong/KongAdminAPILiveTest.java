package cn.tuyucheng.taketoday.kong;

import cn.tuyucheng.taketoday.kong.domain.APIObject;
import cn.tuyucheng.taketoday.kong.domain.ConsumerObject;
import cn.tuyucheng.taketoday.kong.domain.KeyAuthObject;
import cn.tuyucheng.taketoday.kong.domain.PluginObject;
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
@SpringBootTest(webEnvironment = DEFINED_PORT, classes = StockApplication.class)
class KongAdminAPILiveTest {

   private String getStockPrice(String code) {
      try {
         return restTemplate.getForObject(new URI(STR."http://localhost:8080/stock/\{code}"), String.class);
      } catch (Exception ignored) {
      }
      return null;
   }

   @BeforeEach
   void init() {
      System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
   }

   @Autowired
   TestRestTemplate restTemplate;

   @Test
   void givenEndpoint_whenQueryStockPrice_thenPriceCorrect() {
      String response = getStockPrice("btc");
      assertEquals("10000", response);

      response = getStockPrice("eth");
      assertEquals("N/A", response);
   }

   @Test
   void givenKongAdminAPI_whenAddAPI_thenAPIAccessibleViaKong() throws Exception {
      restTemplate.delete("http://localhost:8001/stock-api");

      APIObject stockAPI = new APIObject("stock-api", "stock.api", "http://localhost:9090", "/");
      HttpEntity<APIObject> apiEntity = new HttpEntity<>(stockAPI);
      ResponseEntity<String> addAPIResp = restTemplate.postForEntity("http://localhost:8001", apiEntity, String.class);

      assertEquals(HttpStatus.CREATED, addAPIResp.getStatusCode());

      addAPIResp = restTemplate.postForEntity("http://localhost:8001", apiEntity, String.class);
      assertEquals(HttpStatus.CONFLICT, addAPIResp.getStatusCode());
      String apiListResp = restTemplate.getForObject("http://localhost:8001/", String.class);

      assertTrue(apiListResp.contains("stock-api"));

      HttpHeaders headers = new HttpHeaders();
      headers.set("Host", "stock.api");
      RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI("http://localhost:8000/springbootapp/stock/btc"));
      ResponseEntity<String> stockPriceResp = restTemplate.exchange(requestEntity, String.class);

      assertEquals("10000", stockPriceResp.getBody());
   }

   @Test
   void givenKongAdminAPI_whenAddAPIConsumer_thenAdded() {
      restTemplate.delete("http://localhost:8001/consumers/eugenp");

      ConsumerObject consumer = new ConsumerObject("eugenp");
      HttpEntity<ConsumerObject> addConsumerEntity = new HttpEntity<>(consumer);
      ResponseEntity<String> addConsumerResp = restTemplate.postForEntity("http://localhost:8001/consumers/", addConsumerEntity, String.class);

      assertEquals(HttpStatus.CREATED, addConsumerResp.getStatusCode());

      addConsumerResp = restTemplate.postForEntity("http://localhost:8001/consumers", addConsumerEntity, String.class);
      assertEquals(HttpStatus.CONFLICT, addConsumerResp.getStatusCode());

      String consumerListResp = restTemplate.getForObject("http://localhost:8001/consumers/", String.class);
      assertTrue(consumerListResp.contains("eugenp"));
   }

   @Test
   void givenAPI_whenEnableAuth_thenAnonymousDenied() throws Exception {
      String apiListResp = restTemplate.getForObject("http://localhost:8001/", String.class);
      if (!apiListResp.contains("stock-api")) {
         givenKongAdminAPI_whenAddAPI_thenAPIAccessibleViaKong();
      }

      PluginObject authPlugin = new PluginObject("key-auth");
      ResponseEntity<String> enableAuthResp = restTemplate.postForEntity("http://localhost:8001/stock-api/plugins", new HttpEntity<>(authPlugin), String.class);

      assertTrue(HttpStatus.CREATED == enableAuthResp.getStatusCode() || HttpStatus.CONFLICT == enableAuthResp.getStatusCode());

      String pluginsResp = restTemplate.getForObject("http://localhost:8001/stock-api/plugins", String.class);
      assertTrue(pluginsResp.contains("key-auth"));

      HttpHeaders headers = new HttpHeaders();
      headers.set("Host", "stock.api");
      RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI("http://localhost:8000/stock/btc"));
      ResponseEntity<String> stockPriceResp = restTemplate.exchange(requestEntity, String.class);
      assertEquals(HttpStatus.UNAUTHORIZED, stockPriceResp.getStatusCode());
   }

   @Test
   void givenAPIAuthEnabled_whenAddKey_thenAccessAllowed() throws Exception {
      String apiListResp = restTemplate.getForObject("http://localhost:8001/", String.class);
      if (!apiListResp.contains("stock-api")) {
         givenKongAdminAPI_whenAddAPI_thenAPIAccessibleViaKong();
      }

      String consumerListResp = restTemplate.getForObject("http://localhost:8001/consumers/", String.class);
      if (!consumerListResp.contains("eugenp")) {
         givenKongAdminAPI_whenAddAPIConsumer_thenAdded();
      }

      PluginObject authPlugin = new PluginObject("key-auth");
      ResponseEntity<String> enableAuthResp = restTemplate.postForEntity("http://localhost:8001/stock-api/plugins", new HttpEntity<>(authPlugin), String.class);
      assertTrue(HttpStatus.CREATED == enableAuthResp.getStatusCode() || HttpStatus.CONFLICT == enableAuthResp.getStatusCode());

      final String consumerKey = "eugenp.pass";
      KeyAuthObject keyAuth = new KeyAuthObject(consumerKey);
      ResponseEntity<String> keyAuthResp = restTemplate.postForEntity("http://localhost:8001/consumers/eugenp/key-auth", new HttpEntity<>(keyAuth), String.class);

      assertTrue(HttpStatus.CREATED == keyAuthResp.getStatusCode() || HttpStatus.CONFLICT == keyAuthResp.getStatusCode());

      HttpHeaders headers = new HttpHeaders();
      headers.set("Host", "stock.api");
      headers.set("apikey", consumerKey);
      RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI("http://localhost:8000/springbootapp/stock/btc"));
      ResponseEntity<String> stockPriceResp = restTemplate.exchange(requestEntity, String.class);

      assertEquals("10000", stockPriceResp.getBody());

      headers.set("apikey", "wrongpass");
      requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI("http://localhost:8000/springbootapp/stock/btc"));
      stockPriceResp = restTemplate.exchange(requestEntity, String.class);
      assertEquals(HttpStatus.FORBIDDEN, stockPriceResp.getStatusCode());
   }

   @Test
   void givenAdminAPIProxy_whenAddAPIViaProxy_thenAPIAdded() throws Exception {
      APIObject adminAPI = new APIObject("admin-api", "admin.api", "http://localhost:8001", "/admin-api");
      HttpEntity<APIObject> apiEntity = new HttpEntity<>(adminAPI);
      ResponseEntity<String> addAPIResp = restTemplate.postForEntity("http://localhost:8001", apiEntity, String.class);

      assertTrue(HttpStatus.CREATED == addAPIResp.getStatusCode() || HttpStatus.CONFLICT == addAPIResp.getStatusCode());

      HttpHeaders headers = new HttpHeaders();
      headers.set("Host", "admin.api");
      APIObject baeldungAPI = new APIObject("baeldung-api", "baeldung.com", "http://ww.baeldung.com", "/");
      RequestEntity<APIObject> requestEntity = new RequestEntity<>(baeldungAPI, headers, HttpMethod.POST, new URI("http://localhost:8000/admin-api"));
      addAPIResp = restTemplate.exchange(requestEntity, String.class);

      assertTrue(HttpStatus.CREATED == addAPIResp.getStatusCode() || HttpStatus.CONFLICT == addAPIResp.getStatusCode());
   }
}