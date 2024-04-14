package cn.tuyucheng.taketoday.functional;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.web.server.WebServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

import static org.springframework.web.reactive.function.BodyInserters.fromResource;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

public class FunctionalWebApplicationIntegrationTest {

   private static WebTestClient client;
   private static WebServer server;

   @BeforeClass
   public static void setup() {
      server = new FunctionalWebApplication().start();
      client = WebTestClient.bindToServer()
            .baseUrl(STR."http://localhost:\{server.getPort()}")
            .build();
   }

   @AfterClass
   public static void destroy() {
      server.stop();
   }

   @Test
   public void givenRouter_whenGetTest_thenGotHelloWorld() {
      client.get()
            .uri("/test")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .isEqualTo("helloworld");
   }

   @Test
   public void givenIndexFilter_whenRequestRoot_thenRewrittenToTest() {
      client.get()
            .uri("/")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .isEqualTo("helloworld");
   }

   @Test
   public void givenLoginForm_whenPostValidToken_thenSuccess() {
      MultiValueMap<String, String> formData = new LinkedMultiValueMap<>(1);
      formData.add("user", "tuyucheng");
      formData.add("token", "you_know_what_to_do");

      client.post()
            .uri("/login")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData(formData))
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .isEqualTo("welcome back!");
   }

   @Test
   public void givenLoginForm_whenRequestWithInvalidToken_thenFail() {
      MultiValueMap<String, String> formData = new LinkedMultiValueMap<>(2);
      formData.add("user", "tuyucheng");
      formData.add("token", "try_again");

      client.post()
            .uri("/login")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData(formData))
            .exchange()
            .expectStatus()
            .isBadRequest();
   }

   @Test
   public void givenUploadForm_whenRequestWithMultipartData_thenSuccess() throws Exception {
      Resource resource = new ClassPathResource("/tuyucheng-weekly.png");
      client.post()
            .uri("/upload")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(fromResource(resource))
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .isEqualTo(String.valueOf(resource.contentLength()));
   }

   @Test
   public void givenActors_whenAddActor_thenAdded() {
      client.get()
            .uri("/actor")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBodyList(Actor.class)
            .hasSize(2);

      client.post()
            .uri("/actor")
            .body(fromValue(new Actor("Clint", "Eastwood")))
            .exchange()
            .expectStatus()
            .isOk();

      client.get()
            .uri("/actor")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBodyList(Actor.class)
            .hasSize(3);
   }

   @Test
   public void givenResources_whenAccess_thenGot() {
      client.get()
            .uri("/files/hello.txt")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .isEqualTo("hello");
   }
}