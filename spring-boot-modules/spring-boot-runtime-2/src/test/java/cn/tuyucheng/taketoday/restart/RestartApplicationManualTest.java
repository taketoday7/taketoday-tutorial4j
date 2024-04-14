package cn.tuyucheng.taketoday.restart;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * We have to make sure that while running this test, 8080 and 8090 ports are free.
 * Otherwise it will fail.
 */
class RestartApplicationManualTest {

   private TestRestTemplate restTemplate = new TestRestTemplate();

   @Test
   void givenBootApp_whenRestart_thenOk() throws Exception {
      Application.main(new String[0]);

      ResponseEntity response = restTemplate.exchange("http://localhost:8080/restart",
            HttpMethod.POST, null, Object.class);

      assertEquals(200, response.getStatusCode().value());
   }

   @Test
   void givenBootApp_whenRestartUsingActuator_thenOk() throws Exception {
      Application.main(new String[]{"--server.port=8090"});

      ResponseEntity response = restTemplate.exchange("http://localhost:8090/restartApp",
            HttpMethod.POST, null, Object.class);

      assertEquals(200, response.getStatusCode().value());
   }
}