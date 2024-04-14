package cn.tuyucheng.taketoday.spring.data.persistence.springdatajpadifference;

import cn.tuyucheng.taketoday.spring.data.persistence.springdatajpadifference.model.Employee;

public class TestUtils {

   public static Employee employee(String firstName, String lastname) {
      Employee employee = new Employee();
      employee.setFirstName(firstName);
      employee.setLastName(lastname);
      employee.setEmail(firstName + lastname + "@tuyucheng.com");

      return employee;
   }
}