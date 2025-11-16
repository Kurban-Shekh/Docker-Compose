package com.kurban.Docker.Demo.app.Service;

import com.kurban.Docker.Demo.app.Entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> allEmployees();
    Optional<Employee> employeeById(Long id);
    Employee saveEmployee(Employee employee);
    Optional<Employee> updateEmployee(Long id, Employee employee);
    boolean deleteEmployee(Long id);
}
