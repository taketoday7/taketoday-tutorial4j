package cn.tuyucheng.taketoday.jaxws.server.repository;

import cn.tuyucheng.taketoday.jaxws.server.bottomup.exception.EmployeeAlreadyExists;
import cn.tuyucheng.taketoday.jaxws.server.bottomup.exception.EmployeeNotFound;
import cn.tuyucheng.taketoday.jaxws.server.bottomup.model.Employee;

import java.util.List;

public interface EmployeeRepository {

   List<Employee> getAllEmployees();

   Employee getEmployee(int id) throws EmployeeNotFound;

   Employee updateEmployee(int id, String name) throws EmployeeNotFound;

   boolean deleteEmployee(int id) throws EmployeeNotFound;

   Employee addEmployee(int id, String name) throws EmployeeAlreadyExists;

   int count();
}
