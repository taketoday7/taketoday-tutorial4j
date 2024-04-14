package cn.tuyucheng.taketoday.ndc;

import cn.tuyucheng.taketoday.config.AppConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfiguration.class)
@WebAppConfiguration
class NDCLogIntegrationTest {

   private MockMvc mockMvc;

   @Autowired
   private WebApplicationContext webApplicationContext;

   private Investment investment;

   @BeforeEach
   void setUp() {
      mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

      investment = new Investment();
      investment.setTransactionId("123");
      investment.setOwner("Mark");
      investment.setAmount(1000L);
   }

   @Test
   void givenLog4jLogger_whenNDCAdded_thenResponseOkAndNDCInLog() throws Exception {
      mockMvc.perform(post("/ndc/log4j", investment).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(investment))).andExpect(status().is2xxSuccessful());
   }

   @Test
   void givenLog4j2Logger_whenNDCAdded_thenResponseOkAndNDCInLog() throws Exception {
      mockMvc.perform(post("/ndc/log4j2", investment).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(investment))).andExpect(status().is2xxSuccessful());
   }

   @Test
   void givenJBossLoggerBridge_whenNDCAdded_thenResponseOkAndNDCInLog() throws Exception {
      mockMvc.perform(post("/ndc/jboss-logging", investment).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(investment))).andExpect(status().is2xxSuccessful());
   }
}