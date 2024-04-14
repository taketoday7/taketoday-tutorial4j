package cn.tuyucheng.taketoday.ratelimiting.bucket4japp;

import cn.tuyucheng.taketoday.ratelimiting.bucket4japp.service.PricingPlan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Bucket4jRateLimitApplication.class)
@AutoConfigureMockMvc
class Bucket4jRateLimitIntegrationTest {

   @Autowired
   private MockMvc mockMvc;

   @Test
   void givenRectangleAreaCalculator_whenRequestsWithinRateLimit_thenAccepted() throws Exception {
      RequestBuilder request = post("/api/v1/area/rectangle").contentType(MediaType.APPLICATION_JSON_VALUE)
            .content("{ \"length\": 12, \"width\": 10 }")
            .header("X-api-key", "FX001-UBSZ5YRYQ");

      for (int i = 1; i <= PricingPlan.FREE.bucketCapacity(); i++) {
         mockMvc.perform(request)
               .andExpect(status().isOk())
               .andExpect(header().exists("X-Rate-Limit-Remaining"))
               .andExpect(jsonPath("$.shape", equalTo("rectangle")))
               .andExpect(jsonPath("$.area", equalTo(120d)));
      }
   }

   @Test
   void givenRectangleAreaCalculator_whenRequestRateLimitTriggered_thenRejected() throws Exception {
      RequestBuilder request = post("/api/v1/area/rectangle").contentType(MediaType.APPLICATION_JSON_VALUE)
            .content("{ \"length\": 12, \"width\": 10 }")
            .header("X-api-key", "FX001-ZBSY6YSLP");

      for (int i = 1; i <= PricingPlan.FREE.bucketCapacity(); i++) {
         mockMvc.perform(request); // exhaust limit
      }

      mockMvc.perform(request)
            .andExpect(status().isTooManyRequests())
            .andExpect(status().reason("You have exhausted your API Request Quota"))
            .andExpect(header().exists("X-Rate-Limit-Retry-After-Seconds"));
   }
}