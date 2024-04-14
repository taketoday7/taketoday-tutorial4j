package cn.tuyucheng.taketoday.spring.data.keyvalue.services.test;

import cn.tuyucheng.taketoday.spring.data.keyvalue.SpringDataKeyValueApplication;
import cn.tuyucheng.taketoday.spring.data.keyvalue.repositories.EmployeeRepository;
import cn.tuyucheng.taketoday.spring.data.keyvalue.services.EmployeeService;
import cn.tuyucheng.taketoday.spring.data.keyvalue.vo.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringDataKeyValueApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class EmployeeServicesWithRepositoryIntegrationTest {

   @Autowired
   @Qualifier("employeeServicesWithRepository")
   EmployeeService employeeService;

   @Autowired
   EmployeeRepository employeeRepository;

   static Employee employee1;

   @BeforeAll
   static void setUp() {
      employee1 = new Employee(1, "Karan", "IT", "5000");
   }

   @Test
   void test1_whenEmployeeSaved_thenEmployeeIsAddedToMap() {
      employeeService.save(employee1);
      assertEquals(employeeRepository.findById(1).get(), employee1);
   }

   @Test
   void test2_whenEmployeeGet_thenEmployeeIsReturnedFromMap() {
      Employee employeeFetched = employeeService.get(1).get();
      assertEquals(employeeFetched, employee1);
   }

   @Test
   void test3_whenEmployeesFetched_thenEmployeesAreReturnedFromMap() {
      List<Employee> employees = (List<Employee>) employeeService.fetchAll();
      assertEquals(employees.size(), 1);
      assertEquals(employees.get(0), employee1);
   }

   @Test
   void test4_whenEmployeeUpdated_thenEmployeeIsUpdatedToMap() {
      employee1.setName("Pawan");
      employeeService.update(employee1);
      assertEquals(employeeRepository.findById(1).get().getName(), "Pawan");
   }

   @Test
   void test5_whenEmployeeDeleted_thenEmployeeIsRemovedMap() {
      employeeService.delete(1);
      assertFalse(employeeRepository.findById(1).isPresent());
   }

}
