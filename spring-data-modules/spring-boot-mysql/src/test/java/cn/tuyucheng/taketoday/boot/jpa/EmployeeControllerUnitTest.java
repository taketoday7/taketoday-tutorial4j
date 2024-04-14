package cn.tuyucheng.taketoday.boot.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerUnitTest {

   @MockBean
   private EmployeeRepository employeeRepository;

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private ObjectMapper objectMapper;

   @Test
   void givenEmployeeId_whenGetEmployeeCalled_ThenReturnEmployee() throws Exception {
      Employee employeeExpected = new Employee();
      employeeExpected.setEmpName("Test Emp");
      employeeExpected.setEmpDoj(LocalDate.now());
      employeeExpected.setJobTitle("Manager");

      when(employeeRepository.findById(1234)).thenReturn(Optional.of(employeeExpected));

      MvcResult result = mockMvc.perform(get("/employee/1234"))
            .andExpect(status().isOk()).andReturn();

      Employee employee = objectMapper.readValue(result.getResponse().getContentAsString(), Employee.class);
      assertEquals(employeeExpected.getEmpName(), employee.getEmpName());
      assertEquals(employeeExpected.getJobTitle(), employee.getJobTitle());
      assertEquals(employeeExpected.getEmpDoj(), employee.getEmpDoj());
   }

   @Test
   void givenEmployee_whenCreateEmployeeCalled_ThenReturnEmployee() throws Exception {
      Employee employeeExpected = new Employee();
      employeeExpected.setEmpName("Test Emp");
      employeeExpected.setEmpDoj(LocalDate.now());
      employeeExpected.setJobTitle("Manager");

      when(employeeRepository.save(employeeExpected)).thenReturn(employeeExpected);

      MvcResult result = mockMvc.perform(post("/employee")
            .content(objectMapper.writeValueAsString(employeeExpected))
            .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

      Employee employee = objectMapper.readValue(result.getResponse().getContentAsString(), Employee.class);
      assertEquals(employeeExpected.getEmpName(), employee.getEmpName());
      assertEquals(employeeExpected.getJobTitle(), employee.getJobTitle());
      assertEquals(employeeExpected.getEmpDoj(), employee.getEmpDoj());
   }
}