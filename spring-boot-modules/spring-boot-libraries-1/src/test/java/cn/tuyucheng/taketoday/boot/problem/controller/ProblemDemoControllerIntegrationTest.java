package cn.tuyucheng.taketoday.boot.problem.controller;

import cn.tuyucheng.taketoday.boot.problem.SpringProblemApplication;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SpringProblemApplication.class)
@AutoConfigureMockMvc
class ProblemDemoControllerIntegrationTest {

   @Autowired
   private MockMvc mockMvc;

   @Test
   void whenRequestingAllTasks_thenReturnSuccessfulResponseWithArrayWithTwoTasks() throws Exception {
      mockMvc.perform(get("/tasks").contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(jsonPath("$.length()", equalTo(2)))
            .andExpect(status().isOk());
   }

   @Test
   void whenRequestingExistingTask_thenReturnSuccessfulResponse() throws Exception {
      mockMvc.perform(get("/tasks/1").contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(jsonPath("$.id", equalTo(1)))
            .andExpect(status().isOk());
   }

   @Test
   void whenRequestingMissingTask_thenReturnNotFoundProblemResponse() throws Exception {
      mockMvc.perform(get("/tasks/5").contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
            .andDo(print())
            .andExpect(jsonPath("$.title", equalTo("Not found")))
            .andExpect(jsonPath("$.status", equalTo(404)))
            .andExpect(jsonPath("$.detail", equalTo("Task '5' not found")))
            .andExpect(status().isNotFound());
   }

   @Test
   void whenMakePutCall_thenReturnNotImplementedProblemResponse() throws Exception {
      mockMvc.perform(put("/tasks/1").contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
            .andDo(print())
            .andExpect(jsonPath("$.title", equalTo("Not Implemented")))
            .andExpect(jsonPath("$.status", equalTo(501)))
            .andExpect(status().isNotImplemented());
   }

   @Test
   @Disabled
   void whenMakeDeleteCall_thenReturnForbiddenProblemResponse() throws Exception {
      mockMvc.perform(delete("/tasks/2").contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
            .andDo(print())
            .andExpect(jsonPath("$.title", equalTo("Forbidden")))
            .andExpect(jsonPath("$.status", equalTo(403)))
            .andExpect(jsonPath("$.detail", equalTo("You can't delete this task")))
            .andExpect(status().isForbidden());
   }

   @Test
   void whenMakeGetCallWithInvalidIdFormat_thenReturnBadRequestResponseWithStackTrace() throws Exception {
      mockMvc.perform(get("/tasks/invalid-id").contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
            .andDo(print())
            .andExpect(jsonPath("$.title", equalTo("Bad Request")))
            .andExpect(jsonPath("$.status", equalTo(400)))
            .andExpect(jsonPath("$.stacktrace", notNullValue()))
            .andExpect(status().isBadRequest());
   }

   @Test
   void whenMakeGetCallWithInvalidIdFormat_thenReturnBadRequestResponseWithCause() throws Exception {
      mockMvc.perform(get("/tasks/invalid-id").contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
            .andDo(print())
            .andExpect(jsonPath("$.title", equalTo("Bad Request")))
            .andExpect(jsonPath("$.status", equalTo(400)))
            .andExpect(jsonPath("$.cause", notNullValue()))
            .andExpect(jsonPath("$.cause.title", equalTo("Internal Server Error")))
            .andExpect(jsonPath("$.cause.status", equalTo(500)))
            .andExpect(jsonPath("$.cause.detail", containsString("For input string:")))
            .andExpect(jsonPath("$.cause.stacktrace", notNullValue()))
            .andExpect(status().isBadRequest());
   }
}