package cn.tuyucheng.taketoday.spring.data.keyvalue.services.test;

import cn.tuyucheng.taketoday.spring.data.keyvalue.SpringDataKeyValueApplication;
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
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringDataKeyValueApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class EmployeeServicesWithKeyValueRepositoryIntegrationTest {

   @Autowired
   @Qualifier("employeeServicesWithKeyValueTemplate")
   EmployeeService employeeService;

   @Autowired
   @Qualifier("keyValueTemplate")
   KeyValueTemplate keyValueTemplate;

   static Employee employee1;

   static Employee employee2;

   @BeforeAll
   static void setUp() {
      employee1 = new Employee(1, "Karan", "IT", "5000");
      employee2 = new Employee(2, "Jack", "HR", "2000");
   }

   @Test
   void test1_whenEmployeeSaved_thenEmployeeIsAddedToMap() {
      employeeService.save(employee1);
      assertEquals(keyValueTemplate.findById(1, Employee.class).get(), employee1);
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
      assertEquals(keyValueTemplate.findById(1, Employee.class).get().getName(), "Pawan");
   }

   @Test
   void test5_whenSortedEmployeesFetched_thenEmployeesAreReturnedFromMapInOrder() {
      employeeService.save(employee2);
      List<Employee> employees = (List<Employee>) employeeService.getSortedListOfEmployeesBySalary();
      assertEquals(employees.size(), 2);
      assertEquals(employees.get(0), employee1);
      assertEquals(employees.get(1), employee2);
   }

   @Test
   void test6_whenEmployeeDeleted_thenEmployeeIsRemovedMap() {
      employeeService.delete(1);
      assertFalse(keyValueTemplate.findById(1, Employee.class).isPresent());
   }
}