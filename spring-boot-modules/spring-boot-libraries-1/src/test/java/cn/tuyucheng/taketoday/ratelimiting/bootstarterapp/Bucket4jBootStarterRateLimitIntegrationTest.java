package cn.tuyucheng.taketoday.ratelimiting.bootstarterapp;

import cn.tuyucheng.taketoday.ratelimiting.bucket4japp.service.PricingPlan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Bucket4jRateLimitApplication.class)
@TestPropertySource(properties = "spring.config.location=classpath:ratelimiting/application-bucket4j-starter.yml")
@AutoConfigureMockMvc
class Bucket4jBootStarterRateLimitIntegrationTest {

   @Autowired
   private MockMvc mockMvc;

   @Test
   void givenTriangleAreaCalculator_whenRequestsWithinRateLimit_thenAccepted() throws Exception {
      RequestBuilder request = post("/api/v1/area/triangle").contentType(MediaType.APPLICATION_JSON_VALUE)
            .content("{ \"height\": 8, \"base\": 10 }")
            .header("X-api-key", "FX001-UBSZ5YRYQ");

      for (int i = 1; i <= PricingPlan.FREE.bucketCapacity(); i++) {
         mockMvc.perform(request)
               .andExpect(status().isOk())
               .andExpect(header().exists("X-Rate-Limit-Remaining"))
               .andExpect(jsonPath("$.shape", equalTo("triangle")))
               .andExpect(jsonPath("$.area", equalTo(40d)));
      }
   }

   @Test
   void givenTriangleAreaCalculator_whenRequestRateLimitTriggered_thenRejected() throws Exception {
      RequestBuilder request = post("/api/v1/area/triangle").contentType(MediaType.APPLICATION_JSON_VALUE)
            .content("{ \"height\": 8, \"base\": 10 }")
            .header("X-api-key", "FX001-ZBSY6YSLP");

      for (int i = 1; i <= PricingPlan.FREE.bucketCapacity(); i++) {
         mockMvc.perform(request); // exhaust limit
      }

      mockMvc.perform(request)
            .andExpect(status().isTooManyRequests())
            .andExpect(jsonPath("$.message", equalTo("You have exhausted your API Request Quota")))
            .andExpect(header().exists("X-Rate-Limit-Retry-After-Seconds"));
   }
}